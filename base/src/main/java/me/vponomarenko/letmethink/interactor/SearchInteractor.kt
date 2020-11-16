package me.vponomarenko.letmethink.interactor

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.vponomarenko.letmethink.data.model.Story
import me.vponomarenko.letmethink.extension.replaceAll
import me.vponomarenko.letmethink.utils.searchengine.ISearchEngine
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 26/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class SearchInteractor @Inject constructor(
        private val searchEngine: ISearchEngine
) : ISearchInteractor {

    companion object {
        private const val REGEX = "\\s+"
        private const val REPLACEMENT = " "
    }

    private val result = mutableListOf<Story>()

    override fun search(query: String,
                        showOnlyFavorite: Boolean,
                        showOnlyWatched: Boolean): Observable<List<Story>> =
            Observable
                    .fromCallable {
                        query.trim().replaceAll(REGEX, REPLACEMENT).split(REPLACEMENT).run {
                            return@run if (first().isEmpty()) listOf() else this
                        }
                    }
                    .subscribeOn(Schedulers.computation())
                    .doOnSubscribe { result.clear() }
                    .flatMap { searchEngine.search(it, showOnlyFavorite, showOnlyWatched) }
                    .map {
                        result += it
                        return@map result as List<Story>
                    }
                    .observeOn(AndroidSchedulers.mainThread())

}