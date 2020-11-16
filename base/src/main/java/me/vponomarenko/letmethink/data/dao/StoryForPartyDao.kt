package me.vponomarenko.letmethink.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import io.reactivex.Flowable
import io.reactivex.Single
import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 08/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Dao
interface StoryForPartyDao {

    @Query("SELECT count(*) FROM story WHERE isForParty")
    fun count(): Single<Int>

    @Query("SELECT * FROM story WHERE NOT isFavorite AND isForParty ORDER BY id ASC")
    fun getStories(): Flowable<List<Story>>

    @Query("SELECT * FROM story WHERE isFavorite AND isForParty")
    fun getFavoriteStories(): Flowable<List<Story>>

    /**
     * Sorting
     */
    @Query("SELECT * FROM story WHERE NOT isFavorite AND isForParty ORDER BY id DESC")
    fun getStoriesSortedByIdDesc(): Flowable<List<Story>>

    @Query("SELECT * FROM story WHERE NOT isFavorite AND isForParty ORDER BY isWatched DESC")
    fun getStoriesSortedByIsWatchedDesc(): Flowable<List<Story>>

    @Query("SELECT * FROM story WHERE NOT isFavorite AND isForParty ORDER BY isWatched ASC")
    fun getStoriesSortedByIsWatchedAsc(): Flowable<List<Story>>
}