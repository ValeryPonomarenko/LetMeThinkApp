package me.vponomarenko.letmethink.data.repository

import io.reactivex.Flowable
import io.reactivex.Single
import me.vponomarenko.letmethink.core.enums.ESortType
import me.vponomarenko.letmethink.data.datasource.listofstories.IListOfStoriesDataSource
import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 26/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class StoriesRepository(
    private val storiesDataSource: IListOfStoriesDataSource
) : IStoriesRepository {

    override fun count(): Single<Int> = storiesDataSource.count()

    override fun loadFavoriteStories(): Flowable<List<Story>> =
        storiesDataSource.loadFavoriteStories()

    override fun loadSortedStories(sortingType: ESortType): Flowable<List<Story>> =
        storiesDataSource.loadSortedStories(sortingType)

    override fun saveStories(stories: List<Story>): Single<Boolean> =
        storiesDataSource.saveStories(stories)
}