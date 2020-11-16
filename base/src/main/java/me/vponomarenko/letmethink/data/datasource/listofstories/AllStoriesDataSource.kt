package me.vponomarenko.letmethink.data.datasource.listofstories

import io.reactivex.Flowable
import io.reactivex.Single
import me.vponomarenko.letmethink.core.enums.ESortType
import me.vponomarenko.letmethink.data.dao.StoryDao
import me.vponomarenko.letmethink.data.model.Story
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 26/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class AllStoriesDataSource @Inject constructor(
    private val dao: StoryDao
) : IListOfStoriesDataSource {

    override fun count(): Single<Int> = dao.count()

    override fun loadFavoriteStories(): Flowable<List<Story>> = dao.getFavoriteStories()

    override fun loadSortedStories(sortingType: ESortType): Flowable<List<Story>> =
        when (sortingType) {
            ESortType.ByIndexAsc -> dao.getStories()
            ESortType.ByIndexDesc -> dao.getStoriesSortedByIdDesc()
            ESortType.ByWatchedAsc -> dao.getStoriesSortedByIsWatchedAsc()
            ESortType.ByWatchedDesc -> dao.getStoriesSortedByIsWatchedDesc()
        }

    override fun saveStories(stories: List<Story>): Single<Boolean> =
        Single.fromCallable {
            dao.insertAll(stories)
            true
        }
}