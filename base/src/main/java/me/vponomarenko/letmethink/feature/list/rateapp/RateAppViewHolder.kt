package me.vponomarenko.letmethink.feature.list.rateapp

import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_rate_app.*
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.core.data.rateapp.RateApp
import me.vponomarenko.letmethink.utils.ViewHolder

/**
 * Author: Valery Ponomarenko
 * Date: 24/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class RateAppViewHolder(
    inflater: LayoutInflater,
    container: ViewGroup,
    private val onClickListener: (Int) -> Unit
) : ViewHolder(inflater.inflate(R.layout.item_rate_app, container, false)) {

    fun bind(rateApp: RateApp) {
        button_rate_app.setOnClickListener { onClickListener(rateApp.rateId) }
        button_rate_app_later.setOnClickListener { onClickListener(rateApp.laterId) }
        button_rate_app_never.setOnClickListener { onClickListener(rateApp.neverId) }
    }
}