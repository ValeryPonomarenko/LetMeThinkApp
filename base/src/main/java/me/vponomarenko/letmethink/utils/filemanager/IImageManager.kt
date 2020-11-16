package me.vponomarenko.letmethink.utils.filemanager

import android.graphics.Bitmap
import android.net.Uri
import io.reactivex.Single

/**
 * Author: Valery Ponomarenko
 * Date: 07/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IImageManager {

    fun isImageExist(fileName: String): Boolean

    fun localImageUri(name: String): Uri

    fun loadRemoteImage(name: String): Single<Uri>

    //TODO Change Single to Future
    fun saveBitmap(name: String,
                   image: Bitmap,
                   format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG): Single<Uri>

}