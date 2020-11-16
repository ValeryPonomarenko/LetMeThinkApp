package me.vponomarenko.letmethink.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.data.repository.IStoriesRepository
import me.vponomarenko.letmethink.di.qualifier.AllStories
import me.vponomarenko.letmethink.di.qualifier.IsInstantApp
import me.vponomarenko.letmethink.di.qualifier.StoriesForParty
import me.vponomarenko.letmethink.interactor.IInstantAppInteractor
import me.vponomarenko.letmethink.interactor.ILoadStoriesInteractor
import me.vponomarenko.letmethink.interactor.IStoriesInteractor
import me.vponomarenko.letmethink.interactor.IStoryInteractor
import me.vponomarenko.letmethink.interactor.InstantAppInteractor
import me.vponomarenko.letmethink.interactor.ListOfStoriesInteractor
import me.vponomarenko.letmethink.interactor.LoadStoriesInteractor
import me.vponomarenko.letmethink.interactor.StoriesInteractor
import me.vponomarenko.letmethink.interactor.StoryInteractor
import me.vponomarenko.letmethink.interactor.rateapp.IRateAppInteractor
import me.vponomarenko.letmethink.interactor.rateapp.RateAppInteractor
import javax.inject.Singleton

/**
 * Author: Valery Ponomarenko
 * Date: 08/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
class InteractorsModule {

    @Singleton
    @Provides
    fun provideStoriesInteractor(interactor: StoriesInteractor): IStoriesInteractor = interactor

    @Singleton
    @Provides
    @AllStories
    fun provideAllStoriesInteractor(
        @AllStories repository: IStoriesRepository
    ) = ListOfStoriesInteractor(repository)

    @Singleton
    @Provides
    @StoriesForParty
    fun provideStoriesForPartyInteractor(
        @StoriesForParty repository: IStoriesRepository
    ) = ListOfStoriesInteractor(repository)

    @Singleton
    @Provides
    fun provideStoryInteractor(
        interactor: StoryInteractor
    ): IStoryInteractor = interactor

    @Singleton
    @Provides
    fun provideLoadStoriesInteractor(
        interactor: LoadStoriesInteractor
    ): ILoadStoriesInteractor = interactor

    @Singleton
    @Provides
    fun provideRateAppInteractor(
        interactor: RateAppInteractor
    ): IRateAppInteractor = interactor

    @Singleton
    @Provides
    fun provideInstantAppInteractor(
        interactor: InstantAppInteractor
    ): IInstantAppInteractor = interactor

    @IsInstantApp
    @Provides
    fun provideIsInstantApp(context: Context) = context.resources.getBoolean(R.bool.isInstantApp)
}