package me.vponomarenko.letmethink.data.datasource.user

import me.vponomarenko.letmethink.data.model.User
import me.vponomarenko.letmethink.utils.prefs.IPreferences
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 30/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class UserDataSource @Inject constructor(
    private val preferences: IPreferences
): IUserDataSource {

    override fun getUser() = preferences.getUser()

    override fun updateUser(user: User) {
        preferences.updateUser(user)
    }
}