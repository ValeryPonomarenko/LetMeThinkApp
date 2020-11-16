package me.vponomarenko.letmethink.di.module.main

import android.support.v7.app.AppCompatActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.vponomarenko.letmethink.data.ad.DebugInterstitialAds
import me.vponomarenko.letmethink.data.ad.IFullScreenAd
import me.vponomarenko.letmethink.di.qualifier.InterstitialAd
import me.vponomarenko.letmethink.di.qualifier.RewardedAd
import me.vponomarenko.letmethink.di.scope.ActivityScope
import me.vponomarenko.letmethink.di.scope.FragmentScope
import me.vponomarenko.letmethink.domain.ad.ILoadFullscreenAds
import me.vponomarenko.letmethink.domain.ad.LoadFullscreenAds
import me.vponomarenko.letmethink.feature.main.view.MainActivity
import me.vponomarenko.letmethink.feature.main.view.MainFragment
import me.vponomarenko.letmethink.feature.search.view.SearchFragment
import me.vponomarenko.letmethink.feature.story.view.StoryFragment
import me.vponomarenko.letmethink.feature.update.view.UpdateFragment
import me.vponomarenko.letmethink.routing.MainActivityRouting
import me.vponomarenko.letmethink.routing.base.INavigatorBinder

/**
 * Author: Valery Ponomarenko
 * Date: 20/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Module
abstract class MainActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [MainFragmentModule::class])
    abstract fun mainFragmentInjector(): MainFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [StoryFragmentModule::class])
    abstract fun storyFragmentInjector(): StoryFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [UpdateFragmentModule::class])
    abstract fun updateFragmentInjector(): UpdateFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [SearchFragmentModule::class])
    abstract fun searchFragmentInjector(): SearchFragment

    @ActivityScope
    @Binds
    abstract fun activity(activity: MainActivity): AppCompatActivity

    @ActivityScope
    @Binds
    abstract fun routing(routing: MainActivityRouting): INavigatorBinder

    @ActivityScope
    @Binds
    @RewardedAd
    abstract fun rewardedAd(ad: DebugInterstitialAds): IFullScreenAd

    @ActivityScope
    @Binds
    @InterstitialAd
    abstract fun interstitialAd(ad: DebugInterstitialAds): IFullScreenAd

    @ActivityScope
    @Binds
    abstract fun provideLoadFullscreenAds(useCase: LoadFullscreenAds): ILoadFullscreenAds
}