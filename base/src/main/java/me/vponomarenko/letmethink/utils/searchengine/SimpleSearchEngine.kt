package me.vponomarenko.letmethink.utils.searchengine

import io.reactivex.Observable
import me.vponomarenko.letmethink.data.dao.StoryDao
import me.vponomarenko.letmethink.data.model.Story
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 25/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class SimpleSearchEngine @Inject constructor(private val storyDao: StoryDao) : ISearchEngine {

    companion object {
        private const val LIMIT = 10
    }

    override fun search(keywords: List<String>,
                        showOnlyFavorite: Boolean,
                        showOnlyWatched: Boolean): Observable<List<Story>> =
            storyDao.count()
                    .toObservable()
                    .flatMap { createObservable(it, keywords, showOnlyFavorite, showOnlyWatched) }

    private fun createObservable(storiesNumber: Int,
                                 keywords: List<String>,
                                 showOnlyFavorite: Boolean,
                                 showOnlyWatched: Boolean): Observable<List<Story>> =
            Observable.create { emitter ->
                var offset = 0
                while (offset <= storiesNumber) {
                    val stories = storyDao.getRangeOfStories(offset, LIMIT)
                    val resultedList = mutableListOf<Story>()
                    stories.forEach { story ->
                        if (checkKeywords(story, showOnlyFavorite, showOnlyWatched)) {
                            if (keywords.isEmpty() && (showOnlyFavorite || showOnlyWatched)){
                                resultedList += story
                                return@forEach
                            }

                            run loop@ {
                                keywords.forEach {
                                if (story.title.contains(it, true)
                                        || story.story.contains(it, true)) {
                                    resultedList += story
                                    return@loop
                                }
                            }}
                        }
                    }
                    if (resultedList.isNotEmpty()) {
                        emitter.onNext(resultedList)
                    }
                    if (offset < storiesNumber) {
                        offset += LIMIT
                    }
                }
                emitter.onComplete()
            }

    private fun checkKeywords(story: Story, showOnlyFavorite: Boolean, showOnlyWatched: Boolean) =
            when {
                showOnlyFavorite && showOnlyWatched -> story.isFavorite && story.isWatched
                showOnlyFavorite -> story.isFavorite
                showOnlyWatched -> story.isWatched
                else -> true
            }

}