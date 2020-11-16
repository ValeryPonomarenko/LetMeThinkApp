package me.vponomarenko.letmethink.utils.analytics

import me.vponomarenko.letmethink.core.enums.ESortType
import me.vponomarenko.letmethink.interactor.rateapp.ERateApp

/**
 * Author: Valery Ponomarenko
 * Date: 29/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IAnalyticsManager {

    fun logSearch(query: String)

    fun logContent(id: Int, title: String)

    fun logAddFavorite(id: Int, title: String)

    fun logRemoveFavorite(id: Int, title: String)

    fun logAnswerWatched(id: Int, title: String)

    fun logSortingChanged(sortingType: ESortType)

    fun logRateApp(rateAppType: ERateApp)
}