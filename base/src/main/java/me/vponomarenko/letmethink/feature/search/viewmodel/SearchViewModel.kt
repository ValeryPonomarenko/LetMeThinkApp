package me.vponomarenko.letmethink.feature.search.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import me.vponomarenko.letmethink.Screens
import me.vponomarenko.letmethink.feature.search.viewdata.SearchViewState
import me.vponomarenko.letmethink.feature.story.viewdata.StoryViewTransitionData
import me.vponomarenko.letmethink.interactor.SearchInteractor
import me.vponomarenko.letmethink.utils.analytics.IAnalyticsManager
import me.vponomarenko.letmethink.utils.searchengine.ISearchEngine
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 23/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */
 
class SearchViewModel @Inject constructor(
        private val router: Router,
        private val searchInteractor: SearchInteractor,
        private val analyticsManager: IAnalyticsManager
) : ViewModel() {

    val search = MutableLiveData<SearchViewState>()

    private var subscription: Disposable? = null

    override fun onCleared() {
        super.onCleared()
        subscription?.dispose()
    }

    fun search(query: String, showOnlyFavorite: Boolean, showOnlyWatched: Boolean) {
        analyticsManager.logSearch(query)
        subscription?.dispose()
        subscription = searchInteractor
                .search(query, showOnlyFavorite, showOnlyWatched)
                .doOnSubscribe { search.value = SearchViewState.Result(emptyList(), true) }
                .subscribe({
                    search.value = SearchViewState.Result(it, true)
                }, {
                    Timber.e(it)
                    search.value = SearchViewState.Empty()
                }, {
                    val result = search.value
                    when (result) {
                        is SearchViewState.Result -> {
                            if (result.result.isEmpty()) {
                                search.value = SearchViewState.Empty()
                            } else {
                                search.value = result.copy(isLoading = false)
                            }
                        }
                    }
                })
    }

    fun onStoryClick(storyId: Int) {
        router.navigateTo(Screens.STORY_FRAGMENT, StoryViewTransitionData(storyId))
    }

    fun onBackPressed() {
        router.exit()
    }

}