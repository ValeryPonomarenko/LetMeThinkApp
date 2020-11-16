package me.vponomarenko.letmethink.di.module

import android.content.Context
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import me.vponomarenko.letmethink.utils.ReleaseTree
import timber.log.Timber
import javax.inject.Singleton

/**
 * Author: Valery Ponomarenko
 * Date: 17/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
class LoggingModule {

    @Singleton
    @Provides
    fun provideTimberTree(releaseTree: ReleaseTree): Timber.Tree = releaseTree

    @Singleton
    @Provides
    fun provideCrashlytics(): Crashlytics = Crashlytics()

}