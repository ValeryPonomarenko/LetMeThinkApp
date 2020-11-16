package me.vponomarenko.letmethink.interactor

import io.reactivex.Flowable
import io.reactivex.Single
import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 08/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Deprecated("Use IListOfStoriesInteractor or IStoryInteractor")
interface IStoriesInteractor {

    fun loadStoryById(id: Int): Flowable<Story>

    fun loadOtherStoriesForStory(id: Int): Single<List<Story>>

    fun updateAllStoriesFromRemote(): Single<Boolean>

    fun updateStoryIsFavorite(id: Int, isFavorite: Boolean)

    fun updateStoryIsWatched(id: Int)
}