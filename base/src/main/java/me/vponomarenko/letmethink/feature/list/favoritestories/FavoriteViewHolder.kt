package me.vponomarenko.letmethink.feature.list.favoritestories

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_favorite_stories.*
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.data.model.FavoriteShelf
import me.vponomarenko.letmethink.utils.ViewHolder

/**
 * Author: Valery Ponomarenko
 * Date: 12/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class FavoriteViewHolder(
    inflater: LayoutInflater,
    container: ViewGroup,
    private val adapter: FavoriteStoryAdapter,
    private val onClickListener: (Int) -> Unit
) : ViewHolder(
    inflater.inflate(R.layout.item_favorite_stories, container, false)
) {

    fun bind(favoriteShelf: FavoriteShelf) {
        rv_favorite_stories.layoutManager = LinearLayoutManager(
            itemView.context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        rv_favorite_stories.adapter = adapter
        adapter.stories = favoriteShelf.stories
        adapter.setOnClickListener(onClickListener)
    }
}