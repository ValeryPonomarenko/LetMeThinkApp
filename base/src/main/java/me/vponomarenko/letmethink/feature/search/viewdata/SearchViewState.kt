package me.vponomarenko.letmethink.feature.search.viewdata

import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 25/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

sealed class SearchViewState {

    class Empty : SearchViewState()

    data class Result(val result: List<Story>, val isLoading: Boolean) : SearchViewState()

}