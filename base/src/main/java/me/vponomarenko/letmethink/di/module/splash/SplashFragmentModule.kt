package me.vponomarenko.letmethink.di.module.splash

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.vponomarenko.letmethink.di.annotation.ViewModelKey
import me.vponomarenko.letmethink.feature.splash.viewmodel.SplashViewModel

/**
 * Author: Valery Ponomarenko
 * Date: 10/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
abstract class SplashFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun provideSplashViewModel(viewModel: SplashViewModel): ViewModel

}