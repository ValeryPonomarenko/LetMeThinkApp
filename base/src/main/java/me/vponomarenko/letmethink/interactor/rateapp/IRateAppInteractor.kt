package me.vponomarenko.letmethink.interactor.rateapp

import io.reactivex.Flowable
import me.vponomarenko.letmethink.core.data.rateapp.RateApp

/**
 * Author: Valery Ponomarenko
 * Date: 24/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface IRateAppInteractor {

    fun getRateApp(): Flowable<RateApp>

    /**
     * Returns true if the click was handler by the interactor
     */
    fun onRateAppClick(id: Int): Boolean

    fun updateRateAppState()
}