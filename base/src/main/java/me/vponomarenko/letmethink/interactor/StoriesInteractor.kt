package me.vponomarenko.letmethink.interactor

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.vponomarenko.letmethink.data.model.Story
import me.vponomarenko.letmethink.data.repository.ILocalStoriesRepository
import me.vponomarenko.letmethink.data.repository.IRemoteStoriesRepository
import me.vponomarenko.letmethink.utils.analytics.IAnalyticsManager
import timber.log.Timber
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 08/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Deprecated("IStoriesInteractor is deprecated")
class StoriesInteractor @Inject constructor(
    private val localStoriesRepository: ILocalStoriesRepository,
    private val remoteStoriesRepository: IRemoteStoriesRepository,
    private val analyticsManager: IAnalyticsManager
) : IStoriesInteractor {

    override fun loadStoryById(id: Int): Flowable<Story> =
        localStoriesRepository
            .loadStoryById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun loadOtherStoriesForStory(id: Int): Single<List<Story>> =
        localStoriesRepository
            .loadOtherStoriesForStory(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun updateAllStoriesFromRemote() =
        remoteStoriesRepository
            .loadStories()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .flatMap { saveStories(it) }
            .observeOn(AndroidSchedulers.mainThread())

    override fun updateStoryIsFavorite(id: Int, isFavorite: Boolean) {
        updateStory(id) {
            if (isFavorite) {
                analyticsManager.logAddFavorite(it.id, it.title)
            } else {
                analyticsManager.logRemoveFavorite(it.id, it.title)
            }
            it.apply { it.isFavorite = isFavorite }
        }
    }

    override fun updateStoryIsWatched(id: Int) {
        updateStory(id) {
            analyticsManager.logAnswerWatched(it.id, it.title)
            it.apply { it.isWatched = true }
        }
    }

    private fun saveStories(stories: List<Story>) =
        localStoriesRepository
            .saveStories(stories)

    private fun updateStory(id: Int, updateFun: (Story) -> Story) {
        localStoriesRepository
            .loadStoryById(id)
            .subscribeOn(Schedulers.io())
            .take(1)
            .subscribe({
                localStoriesRepository.updateStory(updateFun(it))
            }, {
                Timber.e(it)
            })
    }
}