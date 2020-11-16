package me.vponomarenko.letmethink.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import me.vponomarenko.letmethink.data.dao.StoryDao
import me.vponomarenko.letmethink.data.dao.StoryForPartyDao
import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 08/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Database(entities = [Story::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun storyDao(): StoryDao

    abstract fun storyForPartyDao(): StoryForPartyDao

}