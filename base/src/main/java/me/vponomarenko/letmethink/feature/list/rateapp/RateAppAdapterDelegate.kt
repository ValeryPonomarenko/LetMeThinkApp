package me.vponomarenko.letmethink.feature.list.rateapp

import android.app.Activity
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import me.vponomarenko.letmethink.core.data.rateapp.RateApp

/**
 * Author: Valery Ponomarenko
 * Date: 24/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class RateAppAdapterDelegate(
    activity: Activity,
    private val onClickListener: (Int) -> Unit
) : AbsListItemAdapterDelegate<RateApp, Any, RateAppViewHolder>() {

    private val inflater = activity.layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup) =
        RateAppViewHolder(inflater, parent, onClickListener)

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int) = item is RateApp

    override fun onBindViewHolder(
        item: RateApp,
        vh: RateAppViewHolder,
        payloads: MutableList<Any>
    ) {
        vh.bind(item)
    }
}