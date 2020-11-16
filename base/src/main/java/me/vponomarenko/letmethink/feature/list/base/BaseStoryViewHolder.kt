package me.vponomarenko.letmethink.feature.list.base

import android.net.Uri
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup
import io.reactivex.disposables.Disposable
import me.vponomarenko.letmethink.utils.ViewHolder
import me.vponomarenko.letmethink.utils.filemanager.IImageManager

/**
 * Author: Valery Ponomarenko
 * Date: 12/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

abstract class BaseStoryViewHolder<in T>(
    inflater: LayoutInflater,
    container: ViewGroup,
    @LayoutRes layoutId: Int,
    protected val imageManager: IImageManager
) : ViewHolder(inflater.inflate(layoutId, container, false)) {

    private var subscription: Disposable? = null

    abstract fun bind(item: T)

    protected abstract fun setImageIntoView(imageUri: Uri)

    open fun onRecycled() {
        subscription?.dispose()
    }

    protected fun loadImage(name: String) {
        if (imageManager.isImageExist(name)) {
            setImageIntoView(imageManager.localImageUri(name))
        } else {
            subscription = imageManager
                .loadRemoteImage(name)
                .subscribe({
                    setImageIntoView(it)
                }, {
                    setImageIntoView(Uri.EMPTY)
                })
        }
    }
}