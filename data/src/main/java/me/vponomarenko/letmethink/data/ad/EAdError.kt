package me.vponomarenko.letmethink.data.ad

/**
 * Author: Valery Ponomarenko
 * Date: 12/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

enum class EAdError(val id: Int) {
    ERROR_CODE_INTERNAL_ERROR(0),
    ERROR_CODE_INVALID_REQUEST(1),
    ERROR_CODE_NETWORK_ERROR(2),
    ERROR_CODE_NO_FILL(3)
}