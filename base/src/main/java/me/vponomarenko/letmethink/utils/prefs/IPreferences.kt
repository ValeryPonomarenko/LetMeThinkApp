package me.vponomarenko.letmethink.utils.prefs

import me.vponomarenko.letmethink.data.model.User

/**
 * Author: Valery Ponomarenko
 * Date: 30/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IPreferences {

    fun getUser(): User

    fun updateUser(user: User)
}