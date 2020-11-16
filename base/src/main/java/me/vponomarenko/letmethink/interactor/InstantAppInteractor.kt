package me.vponomarenko.letmethink.interactor

import me.vponomarenko.letmethink.di.qualifier.IsInstantApp
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 04/08/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class InstantAppInteractor @Inject constructor(
    @IsInstantApp private val isInstantApp: Boolean
) : IInstantAppInteractor {

    private var showedStories = 0

    companion object {
        private const val SHOW_DIALOG_AFTER = 5
        private const val NO_NEED_TO_SHOW_DIALOG = -1
    }

    override fun storyWasOpened() {
        if (showedStories == NO_NEED_TO_SHOW_DIALOG) return
        showedStories++
    }

    override fun showDialog() = isInstantApp && needToShowDialog()

    override fun installAppLater() {
        showedStories = 0
    }

    override fun installApp() {
        showedStories = -1
    }

    private fun needToShowDialog(): Boolean {
        return (showedStories > SHOW_DIALOG_AFTER && showedStories != NO_NEED_TO_SHOW_DIALOG).also {
            showedStories = if (it) 0 else showedStories
        }
    }
}