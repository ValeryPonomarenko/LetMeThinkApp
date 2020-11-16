package me.vponomarenko.letmethink.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_expandable_text.view.*

/**
 * Author: Valery Ponomarenko
 * Date: 27/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class ExpandableTextView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {

    companion object {
        private const val ANIM_DURATION = 300L
        private const val EXPANDED_ICON_ANGLE = 180f
        private const val COLLAPSED_ICON_ANGLE = 0f
    }

    var expandableText: String
        get() = text_textView.text.toString()
        set(value) {
            text_textView.text = value
        }

    var stateChangeListener: ((isExpanded: Boolean) -> Unit)? = null

    init {
        orientation = VERTICAL

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_expandable_text, this, true)

        val typedArray = context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.ExpandableTextView,
            0,
            0
        )

        try {
            text_label.text = typedArray
                .getString(R.styleable.ExpandableTextView_expandableLabelText)
            text_textView.text = typedArray
                .getString(R.styleable.ExpandableTextView_expandableTextViewText)
        } finally {
            typedArray.recycle()
        }

        layout_expandable_view.setOnClickListener {
            onExpandableViewClick(expandable_area.visibility == View.GONE)
        }
    }

    private fun onExpandableViewClick(shouldExpand: Boolean) {
        if (shouldExpand) {
            view_countdown.start {
                text_textView.visibility = View.VISIBLE
                stateChangeListener?.invoke(true)
            }
        } else view_countdown.stop()

        expandable_area.visibility = if (shouldExpand) View.VISIBLE else View.GONE
        if (!shouldExpand) text_textView.visibility = View.INVISIBLE

        val rotationAngle = if (shouldExpand) EXPANDED_ICON_ANGLE else COLLAPSED_ICON_ANGLE
        icon_expand_more.animate().rotation(rotationAngle).setDuration(ANIM_DURATION).start()
    }
}