package me.vponomarenko.letmethink.feature.main.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.Screens
import me.vponomarenko.letmethink.core.data.rateapp.RateApp
import me.vponomarenko.letmethink.core.enums.ESortType
import me.vponomarenko.letmethink.core.resource.IResourceResolver
import me.vponomarenko.letmethink.data.model.FavoriteShelf
import me.vponomarenko.letmethink.data.model.Story
import me.vponomarenko.letmethink.di.qualifier.IsForParty
import me.vponomarenko.letmethink.extension.plusAssign
import me.vponomarenko.letmethink.feature.main.viewdata.MainViewConfig
import me.vponomarenko.letmethink.feature.main.viewdata.MainViewState
import me.vponomarenko.letmethink.feature.search.viewdata.SearchViewTransitionData
import me.vponomarenko.letmethink.feature.story.viewdata.StoryViewTransitionData
import me.vponomarenko.letmethink.interactor.IInstantAppInteractor
import me.vponomarenko.letmethink.interactor.IListOfStoriesInteractor
import me.vponomarenko.letmethink.interactor.rateapp.IRateAppInteractor
import me.vponomarenko.letmethink.utils.analytics.IAnalyticsManager
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 21/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class MainViewModel @Inject constructor(
    private val listOfStoriesInteractor: IListOfStoriesInteractor,
    private val rateAppInteractor: IRateAppInteractor,
    private val router: Router,
    private val analyticsManager: IAnalyticsManager,
    private val resourceResolver: IResourceResolver,
    private val instantAppInteractor: IInstantAppInteractor,
    @IsForParty isForParty: Boolean
) : ViewModel() {

    companion object {
        private const val RATE_APP_POSITION = 11
    }

    val stories = MutableLiveData<MainViewState>()

    val screenConfig: MainViewConfig

    private val subscriptions = CompositeDisposable()

    init {
        screenConfig = MainViewConfig(getScreenTitle(isForParty), getSortingTypes())
        subscriptions +=
            loadListOfStories()
                .doOnSubscribe { stories.value = MainViewState.Loading() }
                .subscribe({
                    stories.value = MainViewState.Loaded(it)
                }, {
                    Timber.e(it)
                })
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.clear()
    }

    fun onStoryClick(storyId: Int) {
        if (!rateAppInteractor.onRateAppClick(storyId)) {
            instantAppInteractor.storyWasOpened()
            router.navigateTo(Screens.STORY_FRAGMENT, StoryViewTransitionData(storyId))
        }
    }

    fun onSearchClick(searchButtonCX: Int, searchButtonCY: Int) {
        router.navigateTo(
            Screens.SEARCH_FRAGMENT,
            SearchViewTransitionData(searchButtonCX, searchButtonCY)
        )
    }

    fun onFilterSelected(sortingType: ESortType) {
        subscriptions.clear()
        subscriptions +=
            loadListOfStories(sortingType)
                .subscribe({
                    stories.value = MainViewState.Loaded(it)
                }, {
                    Timber.e(it)
                })
        analyticsManager.logSortingChanged(sortingType)
    }

    private fun loadListOfStories(sortingType: ESortType = ESortType.ByIndexAsc) =
        Flowable
            .combineLatest(
                listOfStoriesInteractor.loadSortedStories(sortingType),
                listOfStoriesInteractor.loadFavoriteShelf(),
                rateAppInteractor.getRateApp(),
                zip()
            )

    private fun zip() =
        Function3<List<Story>, FavoriteShelf, RateApp, List<Any>> { stories, shelf, rateApp ->
            mutableListOf<Any>().apply {
                if (!shelf.isEmpty() && stories.isNotEmpty()) add(shelf)
                if (stories.isEmpty()) {
                    addAll(shelf.stories)
                } else {
                    addAll(stories)
                }
                if (rateApp.isShown && size > RATE_APP_POSITION) {
                    add(RATE_APP_POSITION, rateApp)
                }
            }
        }

    private fun getScreenTitle(isForTeam: Boolean) =
        resourceResolver
            .getString(if (isForTeam) R.string.title_stories_for_party else R.string.title_stories)

    private fun getSortingTypes() = ESortType.values().toList()
}