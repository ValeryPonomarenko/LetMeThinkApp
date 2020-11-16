package me.vponomarenko.letmethink.feature.list

import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter
import me.vponomarenko.letmethink.feature.list.favoritestories.FavoriteAdapterDelegate
import me.vponomarenko.letmethink.feature.list.favoritestories.FavoriteStoryAdapter
import me.vponomarenko.letmethink.feature.list.rateapp.RateAppAdapterDelegate
import me.vponomarenko.letmethink.feature.list.storybig.StoryBigAdapterDelegate
import me.vponomarenko.letmethink.feature.main.view.MainActivity
import me.vponomarenko.letmethink.utils.filemanager.IImageManager

/**
 * Author: Valery Ponomarenko
 * Date: 12/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class StoriesFeedAdapter(
    activity: MainActivity,
    imageManager: IImageManager,
    adapter: FavoriteStoryAdapter?
) : ListDelegationAdapter<List<Any>>() {

    private var onClickListener: ((Int) -> Unit)? = null

    init {
        delegatesManager
            .addDelegate(StoryBigAdapterDelegate(activity, imageManager, this::onClickListener))
            .addDelegate(RateAppAdapterDelegate(activity, this::onClickListener))
            .apply {
                adapter?.let {
                    addDelegate(
                        FavoriteAdapterDelegate(
                            activity,
                            it,
                            this@StoriesFeedAdapter::onClickListener
                        )
                    )
                }
            }
    }

    override fun setItems(items: List<Any>) {
        super.setItems(items)
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: (Int) -> Unit) {
        onClickListener = listener
    }

    private fun onClickListener(id: Int) {
        onClickListener?.invoke(id)
    }
}