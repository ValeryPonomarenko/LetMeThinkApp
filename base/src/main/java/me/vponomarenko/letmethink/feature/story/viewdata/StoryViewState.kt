package me.vponomarenko.letmethink.feature.story.viewdata

import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 26/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

sealed class StoryViewState {

    class Loading : StoryViewState()

    data class StoryLoaded(val story: Story, val showInstantAppDialog: Boolean) : StoryViewState()
}