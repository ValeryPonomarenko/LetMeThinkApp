package me.vponomarenko.letmethink.feature.main.viewdata

import me.vponomarenko.letmethink.core.enums.ESortType

/**
 * Author: Valery Ponomarenko
 * Date: 26/05/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

data class MainViewConfig(
    val screenTitle: String,
    val sortingTypes: List<ESortType>
)