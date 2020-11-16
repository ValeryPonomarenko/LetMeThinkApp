package me.vponomarenko.letmethink.interactor

import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.vponomarenko.letmethink.data.model.Story
import me.vponomarenko.letmethink.data.repository.IStoryRepository
import me.vponomarenko.letmethink.utils.analytics.IAnalyticsManager
import timber.log.Timber
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 27/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class StoryInteractor @Inject constructor(
    private val storyRepository: IStoryRepository,
    private val analyticsManager: IAnalyticsManager
) : IStoryInteractor {

    override fun loadStoryById(id: Int): Flowable<Story> =
        storyRepository.loadStoryById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun loadOtherStoriesForStory(id: Int): Single<List<Story>> =
        storyRepository
            .loadOtherStoriesForStory(id)
            .subscribeOn(Schedulers.io())
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

    private fun updateStory(id: Int, updateFun: (Story) -> Story) {
        storyRepository
            .loadStoryById(id)
            .subscribeOn(Schedulers.io())
            .take(1)
            .subscribe({
                storyRepository.updateStory(updateFun(it))
            }, {
                Timber.e(it)
            })
    }
}