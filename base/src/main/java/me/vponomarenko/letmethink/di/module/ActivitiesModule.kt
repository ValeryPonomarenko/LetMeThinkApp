package me.vponomarenko.letmethink.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.vponomarenko.letmethink.di.module.main.MainActivityModule
import me.vponomarenko.letmethink.di.module.splash.SplashActivityModule
import me.vponomarenko.letmethink.di.scope.ActivityScope
import me.vponomarenko.letmethink.feature.main.view.MainActivity
import me.vponomarenko.letmethink.feature.splash.view.SplashActivity

/**
 * Author: Valery Ponomarenko
 * Date: 20/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
abstract class ActivitiesModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun splashActivityInjector(): SplashActivity

}