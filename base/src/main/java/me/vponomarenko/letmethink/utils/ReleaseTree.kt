package me.vponomarenko.letmethink.utils

import android.util.Log
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Author: Valery Ponomarenko
 * Date: 19.03.2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Singleton
class ReleaseTree @Inject constructor() : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        t?.takeIf { priority == Log.ERROR }
    }

}