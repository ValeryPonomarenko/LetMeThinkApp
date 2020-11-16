package me.vponomarenko.letmethink.extension

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import me.vponomarenko.letmethink.R

/**
 * Author: Valery Ponomarenko
 * Date: 17/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

fun ImageView.setImageFromUri(context: Context?,
                              imageUri: Uri,
                              fit: Boolean = false,
                              centerCrop: Boolean = false) {
    Picasso.with(context)
            .load(imageUri)
            .placeholder(R.drawable.ic_placeholder)
            .apply {
                if (fit) fit()
                if (centerCrop) centerCrop()
            }
            .into(this)
}