package me.vponomarenko.letmethink.interactor

import android.arch.persistence.room.EmptyResultSetException
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.vponomarenko.letmethink.core.enums.ESortType
import me.vponomarenko.letmethink.data.model.FavoriteShelf
import me.vponomarenko.letmethink.data.model.Story
import me.vponomarenko.letmethink.data.repository.IStoriesRepository

/**
 * Author: Valery Ponomarenko
 * Date: 26/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class ListOfStoriesInteractor constructor(
    private val storiesRepository: IStoriesRepository
) : IListOfStoriesInteractor {

    override fun loadFavoriteShelf(): Flowable<FavoriteShelf> =
        storiesRepository.loadFavoriteStories()
            .subscribeOn(Schedulers.io())
            .onErrorResumeNext { t: Throwable ->
                if (t is EmptyResultSetException) {
                    Flowable.just(listOf())
                } else {
                    Flowable.error(t)
                }
            }
            .map { FavoriteShelf(it) }
            .observeOn(AndroidSchedulers.mainThread())

    override fun loadSortedStories(sortingType: ESortType): Flowable<List<Story>> =
        storiesRepository
            .loadSortedStories(sortingType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}