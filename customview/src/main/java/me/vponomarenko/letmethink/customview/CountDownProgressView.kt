package me.vponomarenko.letmethink.customview

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.view_count_down_progess.view.*
import java.util.concurrent.TimeUnit

/**
 * Author: Valery Ponomarenko
 * Date: 04/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class CountDownProgressView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : RelativeLayout(context, attributeSet) {

    companion object {
        private const val COUNT_DOWN_SEC = 3
    }

    private var subscription: Disposable? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_count_down_progess, this, true)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        subscription?.dispose()
    }

    fun stop() {
        subscription?.dispose()
    }

    fun start(countDownSec: Int = COUNT_DOWN_SEC, onEndListener: () -> Unit) {
        this.visibility = View.VISIBLE
        subscription = Observable
            .interval(0, 1, TimeUnit.SECONDS)
            .take(countDownSec + 1L)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                text_sec_remaining.animate().cancel()
                createAnimation(text_sec_remaining).start()
                text_sec_remaining.text = "${countDownSec - it}"
            }, { /* onError */ }, {
                this.visibility = View.GONE
                onEndListener()
            })
    }

    private fun createAnimation(view: View, scaleTo: Float = 0.6f, alphaTo: Float = 0.6f) =
        view
            .animate()
            .scaleX(scaleTo)
            .scaleY(scaleTo)
            .alpha(alphaTo)
            .setDuration(1000L)
            .apply {
                val resetView = {
                    view.scaleX = 1.0f
                    view.scaleY = 1.0f
                    view.alpha = 1.0f
                }
                setListener(object : Animator.AnimatorListener {
                    override fun onAnimationRepeat(animation: Animator?) {}

                    override fun onAnimationEnd(animation: Animator?) {
                        resetView()
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        resetView()
                    }

                    override fun onAnimationStart(animation: Animator?) {}
                })
            }
}