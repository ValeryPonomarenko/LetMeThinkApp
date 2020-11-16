package me.vponomarenko.letmethink.di.module

import dagger.Binds
import dagger.Module
import me.vponomarenko.letmethink.core.resource.IAdUnitsId
import me.vponomarenko.letmethink.core.resource.IResourceResolver
import me.vponomarenko.letmethink.utils.analytics.DebugAnalyticsManager
import me.vponomarenko.letmethink.utils.analytics.IAnalyticsManager
import me.vponomarenko.letmethink.utils.filemanager.IImageManager
import me.vponomarenko.letmethink.utils.filemanager.InternalImageManager
import me.vponomarenko.letmethink.utils.prefs.AppPreferences
import me.vponomarenko.letmethink.utils.prefs.IPreferences
import me.vponomarenko.letmethink.utils.resource.AdUnitsId
import me.vponomarenko.letmethink.utils.resource.ResourceResolver
import javax.inject.Singleton

/**
 * Author: Valery Ponomarenko
 * Date: 29/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
abstract class UtilsModule {

    @Singleton
    @Binds
    abstract fun provideImageManager(imageManager: InternalImageManager): IImageManager

    @Singleton
    @Binds
    abstract fun provideAnalyticsManager(manager: DebugAnalyticsManager): IAnalyticsManager

    @Singleton
    @Binds
    abstract fun provideResourceResolver(resolver: ResourceResolver): IResourceResolver

    @Singleton
    @Binds
    abstract fun provideAdUnitsId(adUnitsId: AdUnitsId): IAdUnitsId

    @Singleton
    @Binds
    abstract fun providePreferences(preferences: AppPreferences): IPreferences
}