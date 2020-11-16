package me.vponomarenko.letmethink.utils

import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.widget.TextView
import me.vponomarenko.letmethink.BuildConfig
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Author: Valery Ponomarenko
 * Date: 13/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Singleton
class NavigationDrawer @Inject constructor(private val router: Router) {

    private var drawerLayout: DrawerLayout? = null

    fun setDrawer(layout: DrawerLayout, navView: NavigationView) {
        drawerLayout = layout
        navView.getHeaderView(0).findViewById<TextView>(R.id.text_version).apply {
            text = resources.getString(R.string.nav_version, BuildConfig.VERSION_NAME)
        }
        navView.setNavigationItemSelectedListener {
            drawerLayout?.closeDrawers()
            if (it.isChecked) return@setNavigationItemSelectedListener true

            it.isChecked = true

            when (it.itemId) {
                R.id.nav_main -> router.replaceScreen(Screens.MAIN_FRAGMENT)
                R.id.nav_team -> router.replaceScreen(Screens.TEAM_FRAGMENT)
                R.id.nav_update_stories -> router.replaceScreen(Screens.UPDATE_FRAGMENT)
                R.id.nav_rate_up -> router.navigateTo(Screens.PLAY_MARKET)
            }

            return@setNavigationItemSelectedListener true
        }
    }

    fun openDrawer() {
        drawerLayout?.openDrawer(GravityCompat.START)
    }

    fun lock() {
        drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    fun unlock() {
        drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

}