package me.vponomarenko.letmethink.data.datasource.story

import io.reactivex.Flowable
import io.reactivex.Single
import me.vponomarenko.letmethink.data.dao.StoryDao
import me.vponomarenko.letmethink.data.model.Story
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 27/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class StoryDataSource @Inject constructor(
    private val dao: StoryDao
) : IStoryDataSource {

    override fun loadStoryById(id: Int): Flowable<Story> = dao.getStory(id)

    override fun loadOtherStoriesForStory(id: Int): Single<List<Story>> =
        dao.getOtherStoriesForStory(id)

    override fun updateStory(story: Story) {
        dao.update(story)
    }
}