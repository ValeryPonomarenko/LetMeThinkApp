package me.vponomarenko.letmethink.di.module.splash

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.vponomarenko.letmethink.di.scope.ActivityScope
import me.vponomarenko.letmethink.di.scope.FragmentScope
import me.vponomarenko.letmethink.feature.splash.view.SplashFragment
import me.vponomarenko.letmethink.routing.SplashActivityRouting
import me.vponomarenko.letmethink.routing.base.INavigatorBinder

/**
 * Author: Valery Ponomarenko
 * Date: 10/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
abstract class SplashActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [SplashFragmentModule::class])
    abstract fun splashFragmentInjector(): SplashFragment

    @ActivityScope
    @Binds
    abstract fun routing(routing: SplashActivityRouting): INavigatorBinder

}