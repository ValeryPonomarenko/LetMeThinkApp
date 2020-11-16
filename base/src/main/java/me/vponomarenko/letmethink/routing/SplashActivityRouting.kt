package me.vponomarenko.letmethink.routing

import me.vponomarenko.letmethink.Screens
import me.vponomarenko.letmethink.feature.main.view.MainActivity
import me.vponomarenko.letmethink.feature.splash.view.SplashActivity
import me.vponomarenko.letmethink.feature.splash.view.SplashFragment
import me.vponomarenko.letmethink.routing.base.BaseRouting
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 20/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class SplashActivityRouting @Inject constructor(
    activity: SplashActivity,
    navigatorHolder: NavigatorHolder
) : BaseRouting<SplashActivity>(activity, navigatorHolder) {

    override fun createFragment(screenKey: String, data: Any?) =
        when (screenKey) {
            Screens.SPLASH_FRAGMENT -> SplashFragment.newInstance()
            else -> throw Throwable("There is no screen for the given $screenKey")
        }

    override fun isCommandForFragment(command: Command) =
        !(command is Replace && command.screenKey == Screens.MAIN_FRAGMENT)

    override fun consumeCommand(command: Command) {
        if (command is Replace && command.screenKey == Screens.MAIN_FRAGMENT) {
            activity.startActivity(MainActivity.newIntentForUpdate(activity))
            activity.finish()
        }
    }
}