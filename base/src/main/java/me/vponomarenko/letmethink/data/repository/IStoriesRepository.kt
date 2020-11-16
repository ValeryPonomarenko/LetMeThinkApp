package me.vponomarenko.letmethink.data.repository

import io.reactivex.Flowable
import io.reactivex.Single
import me.vponomarenko.letmethink.core.enums.ESortType
import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 26/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IStoriesRepository {
    fun count(): Single<Int>

    fun loadFavoriteStories(): Flowable<List<Story>>

    fun loadSortedStories(sortingType: ESortType): Flowable<List<Story>>

    fun saveStories(stories: List<Story>): Single<Boolean>
}