package me.vponomarenko.letmethink.data.datasource.user

import me.vponomarenko.letmethink.data.model.User

/**
 * Author: Valery Ponomarenko
 * Date: 30/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IUserDataSource {

    fun getUser(): User

    fun updateUser(user: User)
}