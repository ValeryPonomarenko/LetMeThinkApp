package me.vponomarenko.letmethink.data.repository

import me.vponomarenko.letmethink.data.model.User

/**
 * Author: Valery Ponomarenko
 * Date: 29/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IUserRepository {

    fun getUser(): User

    fun updateUser(user: User)
}