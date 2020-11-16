package me.vponomarenko.letmethink.utils.resource

import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.core.resource.IAdUnitsId
import me.vponomarenko.letmethink.core.resource.IResourceResolver
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 11/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class AdUnitsId @Inject constructor(
    private val resolver: IResourceResolver
) : IAdUnitsId {

    override fun rewardedAd(): String = resolver.getString(R.string.admob_rewarded_ad_unit_id)

    override fun interstitialAd(): String = resolver.getString(R.string.admob_interstitial_ad_unit_id)
}