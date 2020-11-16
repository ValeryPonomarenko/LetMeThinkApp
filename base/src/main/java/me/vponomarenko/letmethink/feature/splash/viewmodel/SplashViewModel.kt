package me.vponomarenko.letmethink.feature.splash.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.Screens
import me.vponomarenko.letmethink.core.resource.IResourceResolver
import me.vponomarenko.letmethink.feature.splash.viewdata.SplashViewState
import me.vponomarenko.letmethink.interactor.ILoadStoriesInteractor
import me.vponomarenko.letmethink.interactor.rateapp.IRateAppInteractor
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 10/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class SplashViewModel @Inject constructor(
    private val listOfStoriesInteractor: ILoadStoriesInteractor,
    private val router: Router,
    private val resourceResolver: IResourceResolver,
    rateAppInteractor: IRateAppInteractor
) : ViewModel() {

    val viewState = MutableLiveData<SplashViewState>()

    private var subscription: Disposable? = null

    init {
        viewState.value = SplashViewState.Empty()
        startLoading()
        rateAppInteractor.updateRateAppState()
    }

    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
    }

    fun startLoading() {
        subscription?.dispose()
        subscription =
            listOfStoriesInteractor
                .count()
                .flatMap {
                    if (it == 0) {
                        viewState.value = SplashViewState.LoadingData(
                            resourceResolver.getString(R.string.loading_stories)
                        )
                        listOfStoriesInteractor.loadAndSaveStories()
                    } else {
                        Single.just(it)
                    }
                }
                .subscribe({
                    router.replaceScreen(Screens.MAIN_FRAGMENT)
                }, {
                    viewState.value = SplashViewState.Error(
                        if (it is NoSuchElementException) {
                            resourceResolver.getString(R.string.error_network_connection)
                        } else {
                            resourceResolver.getString(R.string.error_something_went_wrong)
                        }
                    )
                    Timber.e(it)
                })
    }
}