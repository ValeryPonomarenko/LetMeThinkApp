package me.vponomarenko.letmethink.domain.ad

import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import me.vponomarenko.letmethink.core.data.ad.EAdState
import me.vponomarenko.letmethink.data.ad.EAdError
import me.vponomarenko.letmethink.data.ad.IFullScreenAd
import me.vponomarenko.letmethink.di.qualifier.InterstitialAd
import me.vponomarenko.letmethink.di.qualifier.RewardedAd
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 11/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class LoadFullscreenAds @Inject constructor(
    @RewardedAd private val rewardedAd: IFullScreenAd,
    @InterstitialAd private val interstitialAd: IFullScreenAd
) : ILoadFullscreenAds {

    private var emitter: ObservableEmitter<EAdState>? = null

    private var showingAd: IFullScreenAd? = null

    override fun loadAd(): Observable<EAdState> =
        Observable.create<EAdState> {
            emitter = it

            it.setCancellable { emitter = null }

            addSuccessAndErrorListeners(rewardedAd)

            rewardedAd.onErrorListener {
                if (it == EAdError.ERROR_CODE_NO_FILL.id) {
                    addSuccessAndErrorListeners(interstitialAd)
                    interstitialAd.onErrorListener(::showError)

                    interstitialAd.loadAd()
                    showingAd = interstitialAd
                } else {
                    showError(it)
                }
            }

            rewardedAd.loadAd()
            showingAd = rewardedAd
        }

    override fun showAd() {
        showingAd?.showAd()
    }

    private fun addSuccessAndErrorListeners(ad: IFullScreenAd) {
        ad.onSuccessListener {
            emitter?.onNext(EAdState.WATCHED)
            emitter?.onComplete()
        }
        ad.onLoadingListener { isLoading ->
            emitter?.onNext(if (isLoading) EAdState.LOADING else EAdState.READY)
        }
    }

    private fun showError(errorCode: Int) {
        emitter?.onError(Throwable("Ad error code = $errorCode"))
        showingAd = null
    }
}