package me.vponomarenko.letmethink.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.Keep

/**
 * Author: Valery Ponomarenko
 * Date: 21/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Keep
@Entity
data class Story(
    @PrimaryKey
    var id: Int = 0,
    var title: String = "",
    var story: String = "",
    var answer: String = "",
    var imageName: String? = null,
    var isFavorite: Boolean = false,
    var isWatched: Boolean = false,
    var isForParty: Boolean = false
)