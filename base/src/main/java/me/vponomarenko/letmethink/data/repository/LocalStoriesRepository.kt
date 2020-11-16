package me.vponomarenko.letmethink.data.repository

import io.reactivex.Flowable
import io.reactivex.Single
import me.vponomarenko.letmethink.core.enums.ESortType
import me.vponomarenko.letmethink.data.dao.StoryDao
import me.vponomarenko.letmethink.data.model.Story
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 21/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class LocalStoriesRepository @Inject constructor(
    private val storyDao: StoryDao
) : ILocalStoriesRepository {

    override fun count(): Single<Int> = storyDao.count()

    override fun loadStories(): Flowable<List<Story>> = storyDao.getStories()

    override fun loadStoryById(id: Int): Flowable<Story> = storyDao.getStory(id)

    override fun loadOtherStoriesForStory(id: Int): Single<List<Story>> =
        storyDao.getOtherStoriesForStory(id)

    override fun loadFavoriteStories(): Flowable<List<Story>> = storyDao.getFavoriteStories()

    override fun loadSortedStories(sortingType: ESortType): Flowable<List<Story>> =
        when (sortingType) {
            ESortType.ByIndexAsc -> storyDao.getStories()
            ESortType.ByIndexDesc -> storyDao.getStoriesSortedByIdDesc()
            ESortType.ByWatchedAsc -> storyDao.getStoriesSortedByIsWatchedAsc()
            ESortType.ByWatchedDesc -> storyDao.getStoriesSortedByIsWatchedDesc()
        }

    override fun saveStories(stories: List<Story>): Single<Boolean> =
        Single.fromCallable {
            storyDao.insertAll(stories)
            true
        }

    override fun updateStory(story: Story) {
        storyDao.update(story)
    }
}