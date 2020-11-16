package me.vponomarenko.letmethink.interactor.rateapp

/**
 * Author: Valery Ponomarenko
 * Date: 30/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

enum class ERateApp(val id: Int) {
    Now(-300),
    Later(-301),
    Never(-302)
}