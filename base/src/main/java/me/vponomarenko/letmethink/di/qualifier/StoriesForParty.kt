package me.vponomarenko.letmethink.di.qualifier

import javax.inject.Qualifier

/**
 * Author: Valery Ponomarenko
 * Date: 26/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class StoriesForParty(val value: String = "StoriesForParty")