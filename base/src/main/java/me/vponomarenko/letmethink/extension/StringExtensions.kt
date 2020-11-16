package me.vponomarenko.letmethink.extension

import java.util.Collections.replaceAll
import java.util.regex.Pattern


/**
 * Author: Valery Ponomarenko
 * Date: 26/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

fun String.replaceAll(regex: String, replacement: String): String {
    val pattern = Pattern.compile(regex)
    val matcher = pattern.matcher(this)
    matcher.find()
    return matcher.replaceAll(replacement)
}