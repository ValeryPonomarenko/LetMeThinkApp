package me.vponomarenko.letmethink.di.module

import dagger.Binds
import dagger.Module
import me.vponomarenko.letmethink.data.datasource.listofstories.AllStoriesDataSource
import me.vponomarenko.letmethink.data.datasource.listofstories.IListOfStoriesDataSource
import me.vponomarenko.letmethink.data.datasource.listofstories.RemoteStoriesDataSource
import me.vponomarenko.letmethink.data.datasource.listofstories.StoriesForPartyDataSource
import me.vponomarenko.letmethink.data.datasource.story.IStoryDataSource
import me.vponomarenko.letmethink.data.datasource.story.StoryDataSource
import me.vponomarenko.letmethink.data.datasource.user.IUserDataSource
import me.vponomarenko.letmethink.data.datasource.user.UserDataSource
import me.vponomarenko.letmethink.di.qualifier.AllStories
import me.vponomarenko.letmethink.di.qualifier.RemoteStories
import me.vponomarenko.letmethink.di.qualifier.StoriesForParty
import javax.inject.Singleton

/**
 * Author: Valery Ponomarenko
 * Date: 27/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
abstract class DataSourcesModule {

    @Singleton
    @Binds
    abstract fun provideStoryDataSource(dataSource: StoryDataSource): IStoryDataSource

    @Singleton
    @Binds
    @RemoteStories
    abstract fun provideRemoteStoriesDataSource(
        dataSource: RemoteStoriesDataSource
    ): IListOfStoriesDataSource

    @Singleton
    @Binds
    @AllStories
    abstract fun provideAllStoriesDataSource(
        dataSource: AllStoriesDataSource
    ): IListOfStoriesDataSource

    @Singleton
    @Binds
    @StoriesForParty
    abstract fun provideStoriesForPartyDataSource(
        dataSource: StoriesForPartyDataSource
    ): IListOfStoriesDataSource

    @Singleton
    @Binds
    abstract fun provideUserDataSource(dataSOurce: UserDataSource): IUserDataSource
}