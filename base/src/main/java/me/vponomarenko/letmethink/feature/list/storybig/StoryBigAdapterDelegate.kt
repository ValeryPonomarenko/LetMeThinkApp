package me.vponomarenko.letmethink.feature.list.storybig

import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import me.vponomarenko.letmethink.data.model.Story
import me.vponomarenko.letmethink.utils.filemanager.IImageManager

/**
 * Author: Valery Ponomarenko
 * Date: 12/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class StoryBigAdapterDelegate(
    activity: Activity,
    private val imageManager: IImageManager,
    private val onClickListener: (Int) -> Unit
) : AbsListItemAdapterDelegate<Story, Any, StoryBigViewHolder>() {

    private val inflater = activity.layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup) =
        StoryBigViewHolder(inflater, parent, imageManager, onClickListener)

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int) =
        item is Story

    override fun onBindViewHolder(item: Story, vh: StoryBigViewHolder, payloads: MutableList<Any>) {
        vh.bind(item)
    }

    override fun onViewRecycled(vh: RecyclerView.ViewHolder) {
        (vh as? StoryBigViewHolder)?.onRecycled()
    }
}