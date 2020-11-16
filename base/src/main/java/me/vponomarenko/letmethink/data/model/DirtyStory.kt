package me.vponomarenko.letmethink.data.model

import android.support.annotation.Keep

/**
 * Author: Valery Ponomarenko
 * Date: 03/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Keep
data class DirtyStory(
    var id: Int = 0,
    var title: String = "",
    var story: String = "",
    var answer: String = "",
    var imageName: String? = null,
    var forParty: Boolean = false
) {

    fun toStory() = Story(
        id = id,
        title = title,
        story = story,
        answer = answer,
        imageName = imageName,
        isForParty = forParty
    )
}