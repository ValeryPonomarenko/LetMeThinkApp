package me.vponomarenko.letmethink.feature.story.viewdata

import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 03/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

sealed class OtherStoriesViewState {

    class Loading : OtherStoriesViewState()

    data class StoriesLoaded(val stories: List<Story>) : OtherStoriesViewState()

}