package me.vponomarenko.letmethink.di.qualifier

import javax.inject.Qualifier

/**
 * Author: Valery Ponomarenko
 * Date: 12/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class RewardedAd(val value: String = "RewardedVideo")