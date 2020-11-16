package me.vponomarenko.letmethink.di.module

import dagger.Module
import dagger.Provides
import me.vponomarenko.letmethink.data.datasource.listofstories.IListOfStoriesDataSource
import me.vponomarenko.letmethink.data.repository.ILocalStoriesRepository
import me.vponomarenko.letmethink.data.repository.IRemoteStoriesRepository
import me.vponomarenko.letmethink.data.repository.IStoriesRepository
import me.vponomarenko.letmethink.data.repository.IStoryRepository
import me.vponomarenko.letmethink.data.repository.IUserRepository
import me.vponomarenko.letmethink.data.repository.LocalStoriesRepository
import me.vponomarenko.letmethink.data.repository.RemoteStoriesRepository
import me.vponomarenko.letmethink.data.repository.StoriesRepository
import me.vponomarenko.letmethink.data.repository.StoryRepository
import me.vponomarenko.letmethink.data.repository.UserRepository
import me.vponomarenko.letmethink.di.qualifier.AllStories
import me.vponomarenko.letmethink.di.qualifier.RemoteStories
import me.vponomarenko.letmethink.di.qualifier.StoriesForParty
import javax.inject.Singleton

/**
 * Author: Valery Ponomarenko
 * Date: 21/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
class RepositoriesModule {

    @Singleton
    @Provides
    fun provideLocalStoriesRepository(
        repository: LocalStoriesRepository
    ): ILocalStoriesRepository = repository

    @Singleton
    @Provides
    fun provideRemoteStoriesRepository(
        repository: RemoteStoriesRepository
    ): IRemoteStoriesRepository = repository

    @Singleton
    @Provides
    @RemoteStories
    fun provideRemoteRepository(
        @RemoteStories dataSource: IListOfStoriesDataSource
    ): IStoriesRepository = StoriesRepository(dataSource)

    @Singleton
    @Provides
    @AllStories
    fun provideLocalRepository(
        @AllStories dataSource: IListOfStoriesDataSource
    ): IStoriesRepository = StoriesRepository(dataSource)

    @Singleton
    @Provides
    @StoriesForParty
    fun provideStoriesForPartyRepository(
        @StoriesForParty dataSource: IListOfStoriesDataSource
    ): IStoriesRepository = StoriesRepository(dataSource)

    @Singleton
    @Provides
    fun provideStoryRepository(
        repository: StoryRepository
    ): IStoryRepository = repository

    @Singleton
    @Provides
    fun provideUserRepository(
        repository: UserRepository
    ): IUserRepository = repository
}