package me.vponomarenko.letmethink.feature.update.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.Disposable
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.core.data.ad.EAdState
import me.vponomarenko.letmethink.core.resource.IResourceResolver
import me.vponomarenko.letmethink.domain.ad.ILoadFullscreenAds
import me.vponomarenko.letmethink.feature.update.viewdata.UpdateViewState
import me.vponomarenko.letmethink.interactor.IStoriesInteractor
import timber.log.Timber
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 3/18/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class UpdateViewModel @Inject constructor(
    private val storiesInteractor: IStoriesInteractor,
    private val loadFullscreenAd: ILoadFullscreenAds,
    private val resourceResolver: IResourceResolver
) : ViewModel() {

    val updateViewState = MutableLiveData<UpdateViewState>()

    private var subscription: Disposable? = null

    private var needShowAd = false

    private var needReloadAd = false

    init {
        updateViewState.value = UpdateViewState.Start()
        loadAd()
    }

    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
    }

    fun showAd() {
        if (needReloadAd) {
            loadAd()
        }
        needShowAd = true
        showLoadedAd()
    }

    fun onOkClick() {
        updateViewState.value = UpdateViewState.Start()
    }

    private fun loadStories() {
        subscription = storiesInteractor
            .updateAllStoriesFromRemote()
            .doOnSubscribe {
                updateViewState.value = UpdateViewState.Updating(
                    resourceResolver.getString(R.string.loading_stories)
                )
            }
            .subscribe({
                updateViewState.value = UpdateViewState.Done()
            }, {
                Timber.e(it)
                showError()
            })
    }

    private fun showError() {
        needShowAd = false
        needReloadAd = true
        updateViewState.value = UpdateViewState.Error()
    }

    private fun loadAd() {
        needReloadAd = false
        loadFullscreenAd.loadAd()
            .subscribe({
                when (it) {
                    EAdState.WATCHED -> {
                        needShowAd = false
                        needReloadAd = true
                        loadStories()
                    }
                    EAdState.LOADING -> {
                        if (needShowAd) {
                            updateViewState.value = UpdateViewState.Updating(
                                resourceResolver.getString(R.string.loading_ad)
                            )
                        }
                    }
                    EAdState.READY -> {
                        if (needShowAd) {
                            showLoadedAd()
                        }
                    }
                    else -> Timber.d("Error EAdState = ${it?.toString()}")
                }
            }, {
                showError()
            })
    }

    private fun showLoadedAd() {
        loadFullscreenAd.showAd()
        updateViewState.value = UpdateViewState.Updating("")
    }
}