package me.vponomarenko.letmethink.interactor

/**
 * Author: Valery Ponomarenko
 * Date: 04/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IInstantAppInteractor {
    fun storyWasOpened()

    fun showDialog(): Boolean

    fun installAppLater()

    fun installApp() {

    }
}