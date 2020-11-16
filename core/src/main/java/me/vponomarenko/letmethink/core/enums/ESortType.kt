package me.vponomarenko.letmethink.core.enums

import me.vponomarenko.letmethink.core.R

/**
 * Author: Valery Ponomarenko
 * Date: 08/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

enum class ESortType(val title: Int) {
    ByIndexAsc(R.string.sort_by_index_asc),
    ByIndexDesc(R.string.sort_by_index_desc),
    ByWatchedAsc(R.string.sort_by_watched_asc),
    ByWatchedDesc(R.string.sort_by_watched_desc)
}