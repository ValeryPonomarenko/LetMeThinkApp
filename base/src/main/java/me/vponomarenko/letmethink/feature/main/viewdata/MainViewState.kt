package me.vponomarenko.letmethink.feature.main.viewdata

/**
 * Author: Valery Ponomarenko
 * Date: 21/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

sealed class MainViewState {

    class Loading : MainViewState()

    data class Loaded(val stories: List<Any>) : MainViewState()
}