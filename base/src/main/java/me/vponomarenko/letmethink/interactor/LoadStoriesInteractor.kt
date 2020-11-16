package me.vponomarenko.letmethink.interactor

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.vponomarenko.letmethink.core.enums.ESortType
import me.vponomarenko.letmethink.data.repository.IStoriesRepository
import me.vponomarenko.letmethink.di.qualifier.AllStories
import me.vponomarenko.letmethink.di.qualifier.RemoteStories
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 10/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class LoadStoriesInteractor @Inject constructor(
    @RemoteStories private val remoteRepository: IStoriesRepository,
    @AllStories private val localRepository: IStoriesRepository
) : ILoadStoriesInteractor {

    companion object {
        private const val OPERATION_TIME_OUT = 20L
    }

    override fun count(): Single<Int> =
        localRepository.count()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun loadAndSaveStories(): Single<Int> =
        remoteRepository.loadSortedStories(ESortType.ByIndexAsc)
            .subscribeOn(Schedulers.io())
            .take(OPERATION_TIME_OUT, TimeUnit.SECONDS)
            .singleOrError()
            .observeOn(Schedulers.io())
            .flatMap { stories ->
                localRepository.saveStories(stories).map { stories.size }
            }
            .observeOn(AndroidSchedulers.mainThread())
}