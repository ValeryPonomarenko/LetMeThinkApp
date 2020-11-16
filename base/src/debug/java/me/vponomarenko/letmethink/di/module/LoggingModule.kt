package me.vponomarenko.letmethink.di.module

import dagger.Module
import dagger.Provides
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
    fun provideTimberTree(): Timber.Tree = Timber.DebugTree()

}