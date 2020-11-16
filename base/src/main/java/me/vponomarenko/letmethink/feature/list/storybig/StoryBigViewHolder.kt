package me.vponomarenko.letmethink.feature.list.storybig

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_story_big.*
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.data.model.Story
import me.vponomarenko.letmethink.extension.setImageFromUri
import me.vponomarenko.letmethink.feature.list.base.BaseStoryViewHolder
import me.vponomarenko.letmethink.utils.filemanager.IImageManager

/**
 * Author: Valery Ponomarenko
 * Date: 12/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class StoryBigViewHolder(
    inflater: LayoutInflater,
    container: ViewGroup,
    imageManager: IImageManager,
    private val onClickListener: (Int) -> Unit
) : BaseStoryViewHolder<Story>(inflater, container, R.layout.item_story_big, imageManager) {

    override fun bind(item: Story) {
        text_story_title.text = item.title
        text_story_description.text = item.story
        image_favorite.visibility = if (item.isFavorite) View.VISIBLE else View.GONE
        image_watched.visibility = if (item.isWatched) View.VISIBLE else View.GONE
        containerView.setOnClickListener {
            onClickListener(item.id)
        }
        item.imageName?.let { loadImage(it) }
    }

    override fun setImageIntoView(imageUri: Uri) {
        image_story_background
            .setImageFromUri(itemView.context, imageUri, true, true)
    }

    override fun onRecycled() {
        super.onRecycled()
        clearView()
    }

    private fun clearView() {
        Picasso.with(itemView.context).cancelRequest(image_story_background)
        image_story_background.background = null
    }
}