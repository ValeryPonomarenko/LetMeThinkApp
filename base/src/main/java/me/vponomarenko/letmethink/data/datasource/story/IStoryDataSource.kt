package me.vponomarenko.letmethink.data.datasource.story

import io.reactivex.Flowable
import io.reactivex.Single
import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 27/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IStoryDataSource {

    fun loadStoryById(id: Int): Flowable<Story>

    fun loadOtherStoriesForStory(id: Int): Single<List<Story>>

    fun updateStory(story: Story)
}