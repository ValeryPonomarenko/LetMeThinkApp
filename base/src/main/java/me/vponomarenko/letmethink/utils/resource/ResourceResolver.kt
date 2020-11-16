package me.vponomarenko.letmethink.utils.resource

import android.content.Context
import me.vponomarenko.letmethink.core.resource.IResourceResolver
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 26/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class ResourceResolver @Inject constructor(
    val context: Context
) : IResourceResolver {

    override fun getString(id: Int): String = context.getString(id)
}