package me.vponomarenko.letmethink.data.repository

import io.reactivex.Flowable
import io.reactivex.Single
import me.vponomarenko.letmethink.data.datasource.story.IStoryDataSource
import me.vponomarenko.letmethink.data.model.Story
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 27/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class StoryRepository @Inject constructor(
    private val dataSource: IStoryDataSource
) : IStoryRepository {

    override fun loadStoryById(id: Int): Flowable<Story> = dataSource.loadStoryById(id)

    override fun loadOtherStoriesForStory(id: Int): Single<List<Story>> =
        dataSource.loadOtherStoriesForStory(id)

    override fun updateStory(story: Story) {
        dataSource.updateStory(story)
    }
}