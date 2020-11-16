package me.vponomarenko.letmethink.di.module.main

import android.arch.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import me.vponomarenko.letmethink.di.annotation.ViewModelKey
import me.vponomarenko.letmethink.feature.update.viewmodel.UpdateViewModel

/**
 * Author: Valery Ponomarenko
 * Date: 3/18/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
class UpdateFragmentModule {

    @Provides
    @IntoMap
    @ViewModelKey(UpdateViewModel::class)
    fun provideStoryViewModel(viewModel: UpdateViewModel): ViewModel = viewModel
}