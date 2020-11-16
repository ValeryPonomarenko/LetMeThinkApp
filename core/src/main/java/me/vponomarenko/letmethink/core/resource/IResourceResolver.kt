package me.vponomarenko.letmethink.core.resource

/**
 * Author: Valery Ponomarenko
 * Date: 26/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IResourceResolver {

    fun getString(id: Int): String
}