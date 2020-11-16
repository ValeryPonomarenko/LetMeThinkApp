package me.vponomarenko.letmethink.utils.filemanager

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import io.reactivex.Single
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 07/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class InternalImageManager @Inject constructor(
        private val context: Context
//        private val firebaseStorage: FirebaseStorage
) : IImageManager {

    companion object {
        private const val MAX_FILE_SIZE_MB = 2L * 1024 * 1024
        private const val IMAGE_COMPRESSION_RATE = 80
    }

    override fun isImageExist(fileName: String) = createFile(fileName).exists()

    override fun localImageUri(name: String): Uri = Uri.fromFile(createFile(name))

    override fun loadRemoteImage(name: String): Single<Uri> =
            Single.create { emitter ->
                var isSubscribed = true
//                firebaseStorage
//                        .reference
//                        .child(name)
//                        .getBytes(MAX_FILE_SIZE_MB)
//                        .addOnSuccessListener {
//                            emitter.onSuccess(
//                                    save(name, BitmapFactory.decodeByteArray(it, 0, it.size))
//                            )
//                        }
//                        .addOnFailureListener {
//                            if (isSubscribed) emitter.onError(it)
//                        }

                emitter.setCancellable { isSubscribed = false }
            }

    override fun saveBitmap(name: String,
                            image: Bitmap,
                            format: Bitmap.CompressFormat): Single<Uri> =
            Single.fromCallable {
                return@fromCallable save(name, image, format)
            }

    private fun createFile(name: String) = File(context.filesDir, name)

    private fun save(name: String,
                     image: Bitmap,
                     format: Bitmap.CompressFormat = Bitmap.CompressFormat.JPEG): Uri =
            with(createFile(name)) {
                FileOutputStream(this).use {
                    image.compress(format, IMAGE_COMPRESSION_RATE, it)
                }
                return@with Uri.fromFile(this)
            }

}