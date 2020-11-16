package me.vponomarenko.letmethink.extension

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.os.Build
import android.support.annotation.RequiresApi
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateDecelerateInterpolator

/**
 * Author: Valery Ponomarenko
 * Date: 17/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.appearWithCircularReveal(
    cx: Int,
    cy: Int,
    duration: Long = 600L
) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onLayoutChange(
            v: View,
            left: Int,
            top: Int,
            right: Int,
            bottom: Int,
            oldLeft: Int,
            oldTop: Int,
            oldRight: Int,
            oldBottom: Int
        ) {
            v.removeOnLayoutChangeListener(this)
            val radius = Math.max(right, bottom) / 2 + Math.max(right - cx, bottom - cy).toFloat()
            v.startCircularRevealAnimation(cx, cy, 0f, radius, duration)
        }
    })
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun View.startCircularRevealAnimation(
    cx: Int,
    cy: Int,
    startRadius: Float,
    endRadius: Float,
    duration: Long = 600L,
    isReversed: Boolean = false,
    endAction: (() -> Unit)? = null
) {
    val anim =
        ViewAnimationUtils
            .createCircularReveal(this, cx, cy, startRadius, endRadius)

    anim.duration = duration
    if (isReversed) {
        anim.interpolator = AccelerateDecelerateInterpolator()
    }
    endAction?.let {
        anim.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                it.invoke()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
    }
    anim.start()
}

fun View.startColorAnimation(startColor: Int, endColor: Int, duration: Long = 600L) {
    val anim = ValueAnimator()
    anim.setIntValues(startColor, endColor)
    anim.setEvaluator(ArgbEvaluator())
    anim.addUpdateListener {
        this.setBackgroundColor(it.animatedValue as Int)
    }
    anim.duration = duration
    anim.start()
}