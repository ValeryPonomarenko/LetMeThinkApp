package me.vponomarenko.letmethink.data.model

/**
 * Author: Valery Ponomarenko
 * Date: 11/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

data class FavoriteShelf(val stories: List<Story>) {

    val size: Int = stories.size

    fun isEmpty() = stories.isEmpty()

}