package me.vponomarenko.letmethink.data.ad

//import com.google.android.gms.ads.AdListener
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.InterstitialAd

/**
 * Author: Valery Ponomarenko
 * Date: 11/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

//class InterstitialAds @Inject constructor(
//    activity: AppCompatActivity,
//    unitsId: IAdUnitsId
//) : AdListener(), IFullScreenAd, LifecycleObserver {
//
//    private val interstitialVideoAd = InterstitialAd(activity)
//
//    private var successListener: SuccessListener? = null
//    private var errorListener: ErrorListener? = null
//    private var loadingListener: LoadingListener? = null
//
//    init {
//        interstitialVideoAd.adListener = this
//        interstitialVideoAd.adUnitId = unitsId.interstitialAd()
//    }
//
//    @SuppressLint("MissingPermission")
//    override fun loadAd() {
//        if (!interstitialVideoAd.isLoaded) {
//            interstitialVideoAd.loadAd(AdRequest.Builder().build())
//        } else {
//            loadingListener?.invoke(false)
//        }
//    }
//
//    override fun showAd() {
//        if (interstitialVideoAd.isLoaded) {
//            interstitialVideoAd.show()
//        } else {
//            loadingListener?.invoke(true)
//        }
//    }
//
//    override fun onSuccessListener(listener: SuccessListener) {
//        successListener = listener
//    }
//
//    override fun onErrorListener(listener: ErrorListener) {
//        errorListener = listener
//    }
//
//    override fun onLoadingListener(listener: LoadingListener) {
//        loadingListener = listener
//    }
//
//    override fun onAdClosed() {
//        super.onAdClosed()
//        successListener?.invoke()
//    }
//
//    override fun onAdFailedToLoad(errorCode: Int) {
//        super.onAdFailedToLoad(errorCode)
//        errorListener?.invoke(errorCode)
//    }
//
//    override fun onAdLoaded() {
//        super.onAdLoaded()
//        loadingListener?.invoke(false)
//    }
//}