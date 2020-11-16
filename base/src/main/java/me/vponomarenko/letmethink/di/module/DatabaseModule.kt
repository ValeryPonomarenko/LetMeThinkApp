package me.vponomarenko.letmethink.di.module

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import me.vponomarenko.letmethink.data.database.AppDatabase
import me.vponomarenko.letmethink.data.database.DatabaseMigrationFactory
import javax.inject.Singleton

/**
 * Author: Valery Ponomarenko
 * Date: 08/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
class DatabaseModule {

    companion object {
        private const val PREFERENCES_KEY = "me.vponomarenko.letmethink.preferences"
    }

    @Singleton
    @Provides
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, "stories-db")
            .addMigrations(DatabaseMigrationFactory.migrationFrom1to2())
            .build()

    @Singleton
    @Provides
    fun provideStoryDao(database: AppDatabase) = database.storyDao()

    @Singleton
    @Provides
    fun provideStoryForPartyDao(database: AppDatabase) = database.storyForPartyDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
}