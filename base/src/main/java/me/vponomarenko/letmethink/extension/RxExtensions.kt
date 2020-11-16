package me.vponomarenko.letmethink.extension

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Author: Valery Ponomarenko
 * Date: 04/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    this.add(disposable)
}

fun<T> Single<List<T>>.switchIfListEmpty(otherSingle: Single<List<T>>): Single<List<T>> =
    this.flatMap {
        return@flatMap if (it.isEmpty()) otherSingle else Single.just(it)
    }