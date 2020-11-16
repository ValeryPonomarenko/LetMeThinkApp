package me.vponomarenko.letmethink.di.module.main

import android.arch.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import me.vponomarenko.letmethink.di.annotation.ViewModelKey
import me.vponomarenko.letmethink.di.scope.FragmentScope
import me.vponomarenko.letmethink.feature.list.storysmall.StorySmallAdapter
import me.vponomarenko.letmethink.feature.story.view.StoryFragment
import me.vponomarenko.letmethink.feature.story.viewmodel.StoryViewModel
import me.vponomarenko.letmethink.utils.filemanager.IImageManager

/**
 * Author: Valery Ponomarenko
 * Date: 03/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
class StoryFragmentModule {

    @FragmentScope
    @Provides
    fun provideStorySmallAdapter(imageManager: IImageManager) =
            StorySmallAdapter(imageManager)

    @FragmentScope
    @Provides
    fun provideSelectedStoryId(fragment: StoryFragment): Int =
            fragment.storyId

    @Provides
    @IntoMap
    @ViewModelKey(StoryViewModel::class)
    fun provideStoryViewModel(viewModel: StoryViewModel): ViewModel = viewModel

}