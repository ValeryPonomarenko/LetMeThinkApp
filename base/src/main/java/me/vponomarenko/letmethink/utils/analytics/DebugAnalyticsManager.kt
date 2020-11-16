package me.vponomarenko.letmethink.utils.analytics

import me.vponomarenko.letmethink.core.enums.ESortType
import me.vponomarenko.letmethink.interactor.rateapp.ERateApp
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 29/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class DebugAnalyticsManager @Inject constructor(
) : IAnalyticsManager {
    override fun logSearch(query: String) {
    }

    override fun logContent(id: Int, title: String) {
    }

    override fun logAddFavorite(id: Int, title: String) {
    }

    override fun logRemoveFavorite(id: Int, title: String) {
    }

    override fun logAnswerWatched(id: Int, title: String) {
    }

    override fun logSortingChanged(sortingType: ESortType) {
    }

    override fun logRateApp(rateAppType: ERateApp) {
    }

    companion object {
        private const val ADD_FAVORITE_EVENT = "add_to_favorite"
        private const val REMOVE_FAVORITE_EVENT = "remove_from_favorite"
        private const val ANSWER_WATCHED_EVENT = "answer_watched"
        private const val SORTING_CHANGED_EVENT = "sorting_changed"
        private const val RATE_APP_EVENT = "rate_app"
    }
}