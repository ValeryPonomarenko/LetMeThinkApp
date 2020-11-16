package me.vponomarenko.letmethink.utils.prefs

import android.annotation.SuppressLint
import android.content.SharedPreferences
import me.vponomarenko.letmethink.data.model.User
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 30/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class AppPreferences @Inject constructor(
    private val preferences: SharedPreferences
): IPreferences {

    companion object {
        private const val EXTRA_FIRST_START = "me.vponomarenko.letmethink.utils.prefs.firstStart"
        private const val EXTRA_APP_RATED = "me.vponomarenko.letmethink.utils.prefs.appRated"
        private const val EXTRA_SHOW_RUNS_APP = "me.vponomarenko.letmethink.utils.prefs.runsApp"
    }

    override fun getUser() = User(
        preferences.getBoolean(EXTRA_FIRST_START, true),
        preferences.getBoolean(EXTRA_APP_RATED, false),
        preferences.getInt(EXTRA_SHOW_RUNS_APP, 0)
    )

    @SuppressLint("ApplySharedPref")
    override fun updateUser(user: User) {
        preferences.edit().apply {
            putBoolean(EXTRA_FIRST_START, user.firstStart)
            putBoolean(EXTRA_APP_RATED, user.appRated)
            putInt(EXTRA_SHOW_RUNS_APP, user.runsApp)
            commit()
        }
    }
}