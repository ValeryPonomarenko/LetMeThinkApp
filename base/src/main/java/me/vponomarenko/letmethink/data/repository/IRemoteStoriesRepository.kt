package me.vponomarenko.letmethink.data.repository

import io.reactivex.Single
import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 22.03.2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Deprecated("Use IStoriesRepository")
interface IRemoteStoriesRepository {

    fun loadStories(): Single<List<Story>>

}