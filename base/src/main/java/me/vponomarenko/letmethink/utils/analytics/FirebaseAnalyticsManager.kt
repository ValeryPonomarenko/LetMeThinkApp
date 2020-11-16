package me.vponomarenko.letmethink.utils.analytics

/**
 * Author: Valery Ponomarenko
 * Date: 29/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

//class FirebaseAnalyticsManager @Inject constructor(
//    private val firebaseAnalytics: FirebaseAnalytics
//) : IAnalyticsManager {
//
//    companion object {
//        private const val ADD_FAVORITE_EVENT = "add_to_favorite"
//        private const val REMOVE_FAVORITE_EVENT = "remove_from_favorite"
//        private const val ANSWER_WATCHED_EVENT = "answer_watched"
//        private const val SORTING_CHANGED_EVENT = "sorting_changed"
//        private const val RATE_APP_EVENT = "rate_app"
//    }
//
//    override fun logSearch(query: String) {
//        val params = Bundle().apply {
//            putString(FirebaseAnalytics.Param.SEARCH_TERM, query)
//        }
//        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SEARCH, params)
//    }
//
//    override fun logContent(id: Int, title: String) {
//        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM, bundleForContent(id, title))
//    }
//
//    override fun logAddFavorite(id: Int, title: String) {
//        firebaseAnalytics.logEvent(ADD_FAVORITE_EVENT, bundleForContent(id, title))
//    }
//
//    override fun logRemoveFavorite(id: Int, title: String) {
//        firebaseAnalytics.logEvent(REMOVE_FAVORITE_EVENT, bundleForContent(id, title))
//    }
//
//    override fun logAnswerWatched(id: Int, title: String) {
//        firebaseAnalytics.logEvent(ANSWER_WATCHED_EVENT, bundleForContent(id, title))
//    }
//
//    override fun logSortingChanged(sortingType: ESortType) {
//        firebaseAnalytics.logEvent(
//            SORTING_CHANGED_EVENT,
//            Bundle().apply {
//                putString(FirebaseAnalytics.Param.ITEM_NAME, sortingType.name)
//            }
//        )
//    }
//
//    override fun logRateApp(rateAppType: ERateApp) {
//        firebaseAnalytics.logEvent(
//            RATE_APP_EVENT,
//            Bundle().apply {
//                putString(FirebaseAnalytics.Param.ITEM_NAME, rateAppType.name)
//            }
//        )
//    }
//
//    private fun bundleForContent(id: Int, title: String) = Bundle().apply {
//        putString(FirebaseAnalytics.Param.ITEM_ID, id.toString())
//        putString(FirebaseAnalytics.Param.ITEM_NAME, title)
//    }
//}