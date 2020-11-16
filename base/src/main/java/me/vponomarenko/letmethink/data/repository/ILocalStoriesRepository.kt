package me.vponomarenko.letmethink.data.repository

import io.reactivex.Flowable
import io.reactivex.Single
import me.vponomarenko.letmethink.core.enums.ESortType
import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 21/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Deprecated("Use IStoriesRepository")
interface ILocalStoriesRepository {
    fun count(): Single<Int>

    fun loadStories(): Flowable<List<Story>>

    fun loadStoryById(id: Int): Flowable<Story>

    fun loadOtherStoriesForStory(id: Int): Single<List<Story>>

    fun loadFavoriteStories(): Flowable<List<Story>>

    fun loadSortedStories(sortingType: ESortType): Flowable<List<Story>>

    fun saveStories(stories: List<Story>): Single<Boolean>

    fun updateStory(story: Story)
}
