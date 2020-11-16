package me.vponomarenko.letmethink.routing

import android.content.Intent
import android.net.Uri
import com.google.android.instantapps.InstantApps
import me.vponomarenko.letmethink.Screens
import me.vponomarenko.letmethink.feature.main.view.MainActivity
import me.vponomarenko.letmethink.feature.main.view.MainFragment
import me.vponomarenko.letmethink.feature.search.view.SearchFragment
import me.vponomarenko.letmethink.feature.search.viewdata.SearchViewTransitionData
import me.vponomarenko.letmethink.feature.story.view.StoryFragment
import me.vponomarenko.letmethink.feature.story.viewdata.StoryViewTransitionData
import me.vponomarenko.letmethink.feature.update.view.UpdateFragment
import me.vponomarenko.letmethink.routing.base.BaseRouting
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 20/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class MainActivityRouting @Inject constructor(
    activity: MainActivity,
    navigatorHolder: NavigatorHolder
) : BaseRouting<MainActivity>(activity, navigatorHolder) {

    companion object {
        private const val MARKET_URI = "market://details?id="
        private const val HTTP_MARKET_URL = "https://play.google.com/store/apps/details?id="
        private const val HTTPS_INSTANT_URL = "https://vponomarenko-letmethink.firebaseapp.com/"
        private const val INSTANT_REQUEST_CODE = 1000
        private const val INSTANT_REFERRER = "StoryFragment"
    }

    override fun createFragment(screenKey: String, data: Any?) =
        when (screenKey) {
            Screens.MAIN_FRAGMENT -> MainFragment.newInstance()
            Screens.TEAM_FRAGMENT -> MainFragment.newInstance(isForParty = true)
            Screens.UPDATE_FRAGMENT -> UpdateFragment.newInstance()
            Screens.STORY_FRAGMENT ->
                if (data is StoryViewTransitionData)
                    StoryFragment.newInstance(data.storyId)
                else throw Throwable("data is not a StoryViewTransitionData object")
            Screens.SEARCH_FRAGMENT ->
                if (data is SearchViewTransitionData)
                    SearchFragment.newInstance(data)
                else throw Throwable("data is not a SearchViewTransitionData object")
            else -> throw Throwable("There is no screen for the given $screenKey")
        }

    override fun isCommandForFragment(command: Command): Boolean =
        !(command is Forward && command.screenKey in listOf(
            Screens.PLAY_MARKET,
            Screens.INSTANT_INSTALL
        ))

    override fun consumeCommand(command: Command) {
        if (command !is Forward) return
        when (command.screenKey) {
            Screens.PLAY_MARKET -> {
                val appPackageName = activity.packageName
                try {
                    activity.startActivity(createIntentForMarket(appPackageName))
                } catch (e: Exception) {
                    activity.startActivity(createIntentForHttpMarket(appPackageName))
                }
            }
            Screens.INSTANT_INSTALL -> {
                InstantApps.showInstallPrompt(
                    activity,
                    createIntentForInstantApp(),
                    INSTANT_REQUEST_CODE,
                    INSTANT_REFERRER
                )
            }
        }
    }

    private fun createIntentForMarket(packageName: String) =
        Intent(Intent.ACTION_VIEW, Uri.parse("$MARKET_URI$packageName"))

    private fun createIntentForHttpMarket(packageName: String) =
        Intent(Intent.ACTION_VIEW, Uri.parse("$HTTP_MARKET_URL$packageName"))

    private fun createIntentForInstantApp() =
        Intent(Intent.ACTION_VIEW, Uri.parse(HTTPS_INSTANT_URL))
            .addCategory(Intent.CATEGORY_BROWSABLE)
}