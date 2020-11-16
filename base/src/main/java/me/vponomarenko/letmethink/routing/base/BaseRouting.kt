package me.vponomarenko.letmethink.routing.base

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import me.vponomarenko.letmethink.R
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import ru.terrakok.cicerone.commands.Command

/**
 * Author: Valery Ponomarenko
 * Date: 20/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

abstract class BaseRouting<out T: AppCompatActivity>(
        protected val activity: T,
        private val navigationHolder: NavigatorHolder
) : INavigatorBinder {

    protected val fragmentManager: FragmentManager
        get() = activity.supportFragmentManager

    private val navigator by lazy {
        object : SupportFragmentNavigator(fragmentManager, R.id.fragment_container) {

            override fun applyCommand(command: Command) {
                if (isCommandForFragment(command)) {
                    super.applyCommand(command)
                } else {
                    consumeCommand(command)
                }
            }

            override fun createFragment(screenKey: String, data: Any?) =
                    this@BaseRouting.createFragment(screenKey, data)

            override fun exit() {
                activity.finish()
            }

            override fun showSystemMessage(message: String?) {

            }
        }
    }

    abstract fun createFragment(screenKey: String, data: Any?): Fragment

    abstract fun isCommandForFragment(command: Command): Boolean

    abstract fun consumeCommand(command: Command)

    override fun bind() {
        navigationHolder.setNavigator(navigator)
    }

    override fun unbind() {
        navigationHolder.removeNavigator()
    }

}