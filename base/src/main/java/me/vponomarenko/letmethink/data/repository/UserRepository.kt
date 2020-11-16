package me.vponomarenko.letmethink.data.repository

import me.vponomarenko.letmethink.data.datasource.user.IUserDataSource
import me.vponomarenko.letmethink.data.model.User
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 29/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class UserRepository @Inject constructor(
    private val userDataSource: IUserDataSource
) : IUserRepository {

    override fun getUser() = userDataSource.getUser()

    override fun updateUser(user: User) {
        userDataSource.updateUser(user)
    }
}