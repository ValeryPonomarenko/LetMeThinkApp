package me.vponomarenko.letmethink.di.qualifier

import javax.inject.Qualifier

/**
 * Author: Valery Ponomarenko
 * Date: 04/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IsInstantApp(val value: String = "IsInstantApp")