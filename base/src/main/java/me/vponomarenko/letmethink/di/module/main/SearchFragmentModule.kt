package me.vponomarenko.letmethink.di.module.main

import android.arch.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import me.vponomarenko.letmethink.di.annotation.ViewModelKey
import me.vponomarenko.letmethink.di.scope.FragmentScope
import me.vponomarenko.letmethink.feature.list.StoriesFeedAdapter
import me.vponomarenko.letmethink.feature.main.view.MainActivity
import me.vponomarenko.letmethink.feature.search.viewmodel.SearchViewModel
import me.vponomarenko.letmethink.interactor.ISearchInteractor
import me.vponomarenko.letmethink.interactor.SearchInteractor
import me.vponomarenko.letmethink.utils.filemanager.IImageManager
import me.vponomarenko.letmethink.utils.searchengine.ISearchEngine
import me.vponomarenko.letmethink.utils.searchengine.SimpleSearchEngine

/**
 * Author: Valery Ponomarenko
 * Date: 23/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
class SearchFragmentModule {

    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    fun provideSearchViewModel(viewModel: SearchViewModel): ViewModel = viewModel

    @Provides
    @FragmentScope
    fun provideSearchEngine(searchEngine: SimpleSearchEngine): ISearchEngine = searchEngine

    @Provides
    @FragmentScope
    fun provideSearchInteractor(interactor: SearchInteractor): ISearchInteractor = interactor

    @FragmentScope
    @Provides
    fun provideStoriesAndFavoritesAdapter(activity: MainActivity,
                                          imageManager: IImageManager) =
            StoriesFeedAdapter(activity, imageManager, null)

}