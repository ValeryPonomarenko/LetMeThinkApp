package me.vponomarenko.letmethink.feature.main.util

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.customview.FiltersView
import me.vponomarenko.letmethink.extension.makeGone
import me.vponomarenko.letmethink.extension.makeInvisible
import me.vponomarenko.letmethink.extension.makeVisible
import me.vponomarenko.letmethink.extension.startCircularRevealAnimation
import me.vponomarenko.letmethink.extension.startColorAnimation

/**
 * Author: Valery Ponomarenko
 * Date: 11/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class FiltersMenuAnimator constructor(private val activityContext: Context) {

    fun show(fab: View, menu: FiltersView) {
        menu.makeInvisible()
        val endListener = {
            menu.makeVisible()
            fab.makeInvisible()
            startColorAnimator(menu, R.color.colorRed, R.color.colorWhite)
            val fabHalfWidth = fab.width.toFloat() / 2
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startRevealAnimation(menu, fabHalfWidth, menu.width.toFloat()) {
                    menu.show()
                }
            }
        }
        startTranslationAnimation(fab, menu.width, menu.height, endAction = endListener)
    }

    fun hide(fab: View, menu: FiltersView) {
        menu.hide {
            startColorAnimator(menu, R.color.colorWhite, R.color.colorRed)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val fabHalfWidth = fab.width.toFloat() / 2
                startRevealAnimation(menu, menu.width.toFloat(), fabHalfWidth) {
                    menu.makeGone()
                    fab.makeVisible()
                    startTranslationAnimation(fab, menu.width, menu.height, true)
                }
            }
        }
    }

    private fun startColorAnimator(view: View, startColor: Int, endColor: Int) {
        view.startColorAnimation(
            ContextCompat.getColor(activityContext, startColor),
            ContextCompat.getColor(activityContext, endColor)
        )
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startRevealAnimation(
        menu: View,
        startRadius: Float,
        endRadius: Float,
        endAction: (() -> Unit)? = null
    ) {
        menu.startCircularRevealAnimation(
            menu.width / 2,
            menu.height / 2,
            startRadius,
            endRadius,
            endAction = endAction
        )
    }

    private fun startTranslationAnimation(
        fab: View,
        menuWidth: Int,
        menuHeight: Int,
        isReversed: Boolean = false,
        endAction: (() -> Unit)? = null
    ) {
        val multiplier = if (isReversed) 1 else -1
        fab
            .animate()
            .translationXBy((menuWidth - fab.width) / 2f * multiplier)
            .translationYBy((menuHeight - fab.height) / 2f * multiplier)
            .setDuration(150)
            .withEndAction {
                endAction?.invoke()
            }
            .start()
    }
}