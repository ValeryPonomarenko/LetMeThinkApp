package me.vponomarenko.letmethink.feature.update.viewdata

/**
 * Author: Valery Ponomarenko
 * Date: 3/18/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

sealed class UpdateViewState {

    class Start : UpdateViewState()

    data class Updating(val message: String) : UpdateViewState()

    class Done : UpdateViewState()

    class Error : UpdateViewState()

}