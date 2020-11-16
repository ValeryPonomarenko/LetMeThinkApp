package me.vponomarenko.letmethink.di.module

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

/**
 * Author: Valery Ponomarenko
 * Date: 20/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
class RouterModule {

    private val cicerone = Cicerone.create()

    @Provides
    fun provideRouting(): Router = cicerone.router

    @Provides
    fun provideNavigationHolder(): NavigatorHolder = cicerone.navigatorHolder

}