package me.vponomarenko.letmethink.feature.splash.viewdata

/**
 * Author: Valery Ponomarenko
 * Date: 10/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

sealed class SplashViewState {

    class Empty : SplashViewState()

    data class LoadingData(val message: String) : SplashViewState()

    data class Error(val message: String) : SplashViewState()

}