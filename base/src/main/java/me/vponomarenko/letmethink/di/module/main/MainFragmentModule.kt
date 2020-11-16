package me.vponomarenko.letmethink.di.module.main

import android.arch.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import me.vponomarenko.letmethink.di.annotation.ViewModelKey
import me.vponomarenko.letmethink.di.qualifier.AllStories
import me.vponomarenko.letmethink.di.qualifier.IsForParty
import me.vponomarenko.letmethink.di.qualifier.StoriesForParty
import me.vponomarenko.letmethink.di.scope.FragmentScope
import me.vponomarenko.letmethink.feature.list.StoriesFeedAdapter
import me.vponomarenko.letmethink.feature.list.favoritestories.FavoriteStoryAdapter
import me.vponomarenko.letmethink.feature.main.util.FiltersMenuAnimator
import me.vponomarenko.letmethink.feature.main.view.MainActivity
import me.vponomarenko.letmethink.feature.main.view.MainFragment
import me.vponomarenko.letmethink.feature.main.viewmodel.MainViewModel
import me.vponomarenko.letmethink.interactor.IListOfStoriesInteractor
import me.vponomarenko.letmethink.interactor.ListOfStoriesInteractor
import me.vponomarenko.letmethink.utils.filemanager.IImageManager

/**
 * Author: Valery Ponomarenko
 * Date: 21/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
class MainFragmentModule {

    @FragmentScope
    @Provides
    fun provideFavoriteStoryAdapter(imageManager: IImageManager) =
        FavoriteStoryAdapter(imageManager)

    @FragmentScope
    @Provides
    fun provideStoriesAndFavoritesAdapter(
        activity: MainActivity,
        imageManager: IImageManager,
        adapter: FavoriteStoryAdapter
    ) = StoriesFeedAdapter(activity, imageManager, adapter)

    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(viewModel: MainViewModel): ViewModel = viewModel

    @FragmentScope
    @Provides
    fun provideFiltersMenuAnimator(activity: MainActivity) = FiltersMenuAnimator(activity)

    @FragmentScope
    @Provides
    @IsForParty
    fun provideIsForTeam(fragment: MainFragment) = fragment.isForParty()

    @FragmentScope
    @Provides
    fun provideListOfAllStoriesInteractor(
        @IsForParty isForParty: Boolean,
        @AllStories allStories: ListOfStoriesInteractor,
        @StoriesForParty storiesForParty: ListOfStoriesInteractor
    ): IListOfStoriesInteractor = if (isForParty) storiesForParty else allStories
}