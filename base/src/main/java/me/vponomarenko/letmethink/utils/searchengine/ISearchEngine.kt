package me.vponomarenko.letmethink.utils.searchengine

import io.reactivex.Observable
import me.vponomarenko.letmethink.data.model.Story

/**
 * Author: Valery Ponomarenko
 * Date: 25/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface ISearchEngine {

    fun search(keywords: List<String>,
               showOnlyFavorite: Boolean,
               showOnlyWatched: Boolean): Observable<List<Story>>

}