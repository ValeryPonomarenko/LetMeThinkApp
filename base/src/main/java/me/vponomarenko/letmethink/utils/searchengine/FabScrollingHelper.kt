package me.vponomarenko.letmethink.utils.searchengine

import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.RecyclerView
import android.view.View
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 06/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class FabScrollingHelper @Inject constructor() : RecyclerView.OnScrollListener() {

    private var fab: FloatingActionButton? = null
    private var fabMenu: View? = null

    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        if (dy > 0) {
            fab?.hide()
        } else if (dy < 0) {
            if (fabMenu?.visibility != View.VISIBLE) {
                fab?.show()
            }
        }
    }

    fun install(fab: FloatingActionButton, fabMenu: View) {
        this.fab = fab
        this.fabMenu = fabMenu
    }

    fun release() {
        fab = null
        fabMenu = null
    }

}