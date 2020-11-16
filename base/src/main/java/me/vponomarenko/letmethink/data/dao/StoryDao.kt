package me.vponomarenko.letmethink.data.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Flowable
import io.reactivex.Single
import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 08/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Dao
interface StoryDao {

    @Query("SELECT count(*) FROM story")
    fun count(): Single<Int>

    @Query("SELECT * FROM story WHERE NOT isFavorite ORDER BY id ASC")
    fun getStories(): Flowable<List<Story>>

    @Query("SELECT * FROM story WHERE id = :id")
    fun getStory(id: Int): Flowable<Story>

    @Query("SELECT * FROM story LIMIT :limit OFFSET :offset")
    fun getRangeOfStories(offset: Int, limit: Int): List<Story>

    @Query("SELECT * FROM story WHERE isFavorite")
    fun getFavoriteStories(): Flowable<List<Story>>

    @Query("SELECT * FROM story WHERE id <> :id ORDER BY random() LIMIT 6")
    fun getOtherStoriesForStory(id: Int): Single<List<Story>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(stories: List<Story>)

    @Update
    fun update(story: Story)

    /**
     * Sorting
     */
    @Query("SELECT * FROM story WHERE NOT isFavorite ORDER BY id DESC")
    fun getStoriesSortedByIdDesc(): Flowable<List<Story>>

    @Query("SELECT * FROM story WHERE NOT isFavorite ORDER BY isWatched DESC")
    fun getStoriesSortedByIsWatchedDesc(): Flowable<List<Story>>

    @Query("SELECT * FROM story WHERE NOT isFavorite ORDER BY isWatched ASC")
    fun getStoriesSortedByIsWatchedAsc(): Flowable<List<Story>>
}