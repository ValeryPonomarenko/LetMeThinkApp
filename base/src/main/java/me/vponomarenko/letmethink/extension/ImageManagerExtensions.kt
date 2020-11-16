package me.vponomarenko.letmethink.extension

import android.content.Context
import android.widget.ImageView
import io.reactivex.disposables.Disposable
import me.vponomarenko.letmethink.utils.filemanager.IImageManager
import timber.log.Timber

/**
 * Author: Valery Ponomarenko
 * Date: 17/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

fun IImageManager.rxLoadImage(context: Context?, name: String, intoView: ImageView): Disposable? {
    return if (isImageExist(name)) {
        intoView.setImageFromUri(context, localImageUri(name))
        null
    } else {
        loadRemoteImage(name)
                .subscribe({
                    intoView.setImageFromUri(context, it)
                }, {
                    Timber.e(it)
                })
    }
}