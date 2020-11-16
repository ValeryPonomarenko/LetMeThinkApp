package me.vponomarenko.letmethink.feature.list.storysmall

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import me.vponomarenko.letmethink.data.model.Story
import me.vponomarenko.letmethink.utils.filemanager.IImageManager

/**
 * Author: Valery Ponomarenko
 * Date: 12/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class StorySmallAdapter(
        private val imageManager: IImageManager
) : RecyclerView.Adapter<StorySmallViewHolder>() {

    var stories: List<Story>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private var onClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            StorySmallViewHolder(LayoutInflater.from(parent.context), parent, imageManager) {
                onClickListener?.invoke(it)
            }

    override fun getItemCount() = stories?.size ?: 0

    override fun onBindViewHolder(holder: StorySmallViewHolder, position: Int) {
        stories?.get(position)?.let {
            holder.bind(it)
        }
    }

    fun setOnClickListener(listener: (Int) -> Unit) {
        onClickListener = listener
    }

}