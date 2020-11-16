package me.vponomarenko.letmethink.feature.list.favoritestories

import android.app.Activity
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate
import me.vponomarenko.letmethink.data.model.FavoriteShelf

/**
 * Author: Valery Ponomarenko
 * Date: 12/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class FavoriteAdapterDelegate(
    activity: Activity,
    private val favoriteStoryAdapter: FavoriteStoryAdapter,
    private val onClickListener: (Int) -> Unit
) : AbsListItemAdapterDelegate<FavoriteShelf, Any, FavoriteViewHolder>() {

    private val inflater = activity.layoutInflater

    override fun onCreateViewHolder(parent: ViewGroup) =
        FavoriteViewHolder(inflater, parent, favoriteStoryAdapter, onClickListener)

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int) =
        item is FavoriteShelf

    override fun onBindViewHolder(
        item: FavoriteShelf,
        vh: FavoriteViewHolder,
        payloads: MutableList<Any>
    ) {
        vh.bind(item)
    }
}