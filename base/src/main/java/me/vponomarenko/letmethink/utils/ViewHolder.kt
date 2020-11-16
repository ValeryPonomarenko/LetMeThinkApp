package me.vponomarenko.letmethink.utils

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.extensions.LayoutContainer

/**
 * Author: Valery Ponomarenko
 * Date: 30/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

abstract class ViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer