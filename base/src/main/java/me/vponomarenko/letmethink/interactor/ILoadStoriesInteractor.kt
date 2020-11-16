package me.vponomarenko.letmethink.interactor

import io.reactivex.Single

/**
 * Author: Valery Ponomarenko
 * Date: 10/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface ILoadStoriesInteractor {

    /*
    * Returns the number of stories in local database
    */
    fun count(): Single<Int>

    /*
    * Returns the number of saved stories
    */
    fun loadAndSaveStories(): Single<Int>
}