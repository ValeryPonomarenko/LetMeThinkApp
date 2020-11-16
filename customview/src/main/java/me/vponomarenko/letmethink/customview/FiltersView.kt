package me.vponomarenko.letmethink.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.RadioButton
import kotlinx.android.synthetic.main.view_filers.view.*
import me.vponomarenko.letmethink.core.enums.ESortType

/**
 * Author: Valery Ponomarenko
 * Date: 08/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class FiltersView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : LinearLayout(context, attributeSet) {

    companion object {
        private const val ANIM_DURATION = 150L
        private const val ALPHA_VISIBLE = 1f
        private const val ALPHA_INVISIBLE = 0f
        private const val TRANSLATION_APPEAR = 20f
        private const val TRANSLATION_DISAPPEAR = -20f
    }

    var onClickListener: ((ESortType) -> Unit)? = null

    init {
        orientation = VERTICAL

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.view_filers, this, true)
    }

    fun setFilters(filters: List<ESortType>) {
        val selectedSortType = filters.first()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        filters.forEachIndexed { index, sortType ->
            val button = inflater.inflate(
                R.layout.partial_filter_radio_button,
                radio_group,
                false
            ) as RadioButton
            button.id = index
            button.setText(sortType.title)
            button.isChecked = sortType == selectedSortType
            radio_group.addView(button)
        }

        radio_group.setOnCheckedChangeListener { group, checkedId ->
            if (group.isShown) {
                onClickListener?.invoke(filters[checkedId])
            }
        }
    }

    fun show() {
        radio_group.top = radio_group.top - TRANSLATION_APPEAR.toInt()
        radio_group
            .animate()
            .translationYBy(TRANSLATION_APPEAR)
            .alpha(ALPHA_VISIBLE)
            .setDuration(ANIM_DURATION)
            .start()
    }

    fun hide(endAction: (() -> Unit)? = null) {
        radio_group
            .animate()
            .translationYBy(TRANSLATION_DISAPPEAR)
            .alpha(ALPHA_INVISIBLE)
            .setDuration(ANIM_DURATION)
            .apply {
                if (endAction != null) withEndAction(endAction)
            }
            .start()
    }
}