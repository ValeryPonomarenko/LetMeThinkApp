package me.vponomarenko.letmethink.interactor

import io.reactivex.Observable
import me.vponomarenko.letmethink.data.model.Story

/**
* Author: Valery Ponomarenko
* Date: 26/03/2018
* LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
*/

interface ISearchInteractor {

    fun search(query: String,
               showOnlyFavorite: Boolean,
               showOnlyWatched: Boolean): Observable<List<Story>>

}