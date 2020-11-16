package me.vponomarenko.letmethink.interactor

import io.reactivex.Flowable
import io.reactivex.Single
import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 27/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IStoryInteractor {

    fun loadStoryById(id: Int): Flowable<Story>

    fun loadOtherStoriesForStory(id: Int): Single<List<Story>>

    fun updateStoryIsFavorite(id: Int, isFavorite: Boolean)

    fun updateStoryIsWatched(id: Int)
}