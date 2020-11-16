package me.vponomarenko.letmethink.domain.ad

import io.reactivex.Observable
import me.vponomarenko.letmethink.core.data.ad.EAdState

/**
 * Author: Valery Ponomarenko
 * Date: 11/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

interface ILoadFullscreenAds {

    fun loadAd(): Observable<EAdState>

    fun showAd()

}