package me.vponomarenko.letmethink.interactor

import io.reactivex.Flowable
import me.vponomarenko.letmethink.core.enums.ESortType
import me.vponomarenko.letmethink.data.model.FavoriteShelf
import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 26/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IListOfStoriesInteractor {

    fun loadFavoriteShelf(): Flowable<FavoriteShelf>

    fun loadSortedStories(sortingType: ESortType): Flowable<List<Story>>

}