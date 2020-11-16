package me.vponomarenko.letmethink.feature.story.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import me.vponomarenko.letmethink.Screens
import me.vponomarenko.letmethink.extension.plusAssign
import me.vponomarenko.letmethink.feature.story.viewdata.OtherStoriesViewState
import me.vponomarenko.letmethink.feature.story.viewdata.StoryViewState
import me.vponomarenko.letmethink.feature.story.viewdata.StoryViewTransitionData
import me.vponomarenko.letmethink.interactor.IInstantAppInteractor
import me.vponomarenko.letmethink.interactor.IStoryInteractor
import me.vponomarenko.letmethink.utils.analytics.IAnalyticsManager
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 26/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class StoryViewModel @Inject constructor(
    private val storyInteractor: IStoryInteractor,
    private val storyId: Int,
    private val router: Router,
    private val analyticsManager: IAnalyticsManager,
    private val instantAppInteractor: IInstantAppInteractor
) : ViewModel() {

    val story = MutableLiveData<StoryViewState>()
    val otherStories = MutableLiveData<OtherStoriesViewState>()

    private val subscriptions = CompositeDisposable()

    private var logEventOnce: ((Int, String) -> Unit)? = { id, title ->
        analyticsManager.logContent(id, title)
    }

    init {
        subscriptions += storyInteractor
            .loadStoryById(storyId)
            .doOnSubscribe { story.value = StoryViewState.Loading() }
            .subscribe({
                story.value = StoryViewState.StoryLoaded(it, showInstantAppDialog())
                logEvent(it.id, it.title)
            }, {
                Timber.e(it)
            })

        subscriptions += storyInteractor
            .loadOtherStoriesForStory(storyId)
            .doOnSubscribe { otherStories.value = OtherStoriesViewState.Loading() }
            .subscribe({
                otherStories.value = OtherStoriesViewState.StoriesLoaded(it)
            }, {
                Timber.e(it)
            })
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

    fun onFavoriteClick(isFavorite: Boolean) {
        storyInteractor.updateStoryIsFavorite(storyId, isFavorite)
    }

    fun onAnswerWatcher() {
        storyInteractor.updateStoryIsWatched(storyId)
    }

    fun onStoryClick(id: Int) {
        instantAppInteractor.storyWasOpened()
        router.replaceScreen(Screens.STORY_FRAGMENT, StoryViewTransitionData(id))
    }

    fun onInstallAppClick() {
        instantAppInteractor.installApp()
        router.navigateTo(Screens.INSTANT_INSTALL)
    }

    fun onInstallAppLaterClick() {
        instantAppInteractor.installAppLater()
    }

    fun onBackPressed() {
        router.exit()
    }

    private fun logEvent(id: Int, title: String) {
        logEventOnce?.invoke(id, title)
        logEventOnce = null
    }

    private fun showInstantAppDialog() = instantAppInteractor.showDialog()
}