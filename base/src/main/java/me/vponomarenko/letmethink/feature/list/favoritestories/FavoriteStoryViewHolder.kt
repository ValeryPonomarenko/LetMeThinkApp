package me.vponomarenko.letmethink.feature.list.favoritestories

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favorite_story.view.*
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.data.model.Story
import me.vponomarenko.letmethink.extension.setImageFromUri
import me.vponomarenko.letmethink.feature.list.base.BaseStoryViewHolder
import me.vponomarenko.letmethink.utils.filemanager.IImageManager

/**
 * Author: Valery Ponomarenko
 * Date: 07/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class FavoriteStoryViewHolder(
    inflater: LayoutInflater,
    container: ViewGroup,
    imageManager: IImageManager,
    private val onClickListener: (Int) -> Unit
) : BaseStoryViewHolder<Story>(inflater, container, R.layout.item_favorite_story, imageManager) {

    override fun bind(item: Story) {
        itemView.text_story_title.text = item.title
        itemView.image_watched.visibility = if (item.isWatched) View.VISIBLE else View.GONE
        itemView.setOnClickListener {
            onClickListener(item.id)
        }
        item.imageName?.let { loadImage(it) }
    }

    override fun setImageIntoView(imageUri: Uri) {
        itemView.image_story.setImageFromUri(itemView.context, imageUri)
    }

    override fun onRecycled() {
        super.onRecycled()
        Picasso.with(itemView.context).cancelRequest(itemView.image_story)
        itemView.image_story.background = null
    }
}