package me.vponomarenko.letmethink.data.ad

/**
 * Author: Valery Ponomarenko
 * Date: 11/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

//class RewardedAds @Inject constructor(
//    private val activity: AppCompatActivity,
//    private val unitsId: IAdUnitsId
//) : IFullScreenAd, RewardedVideoAdListener, LifecycleObserver {
//
//    private val rewardedVideoAd: RewardedVideoAd = MobileAds.getRewardedVideoAdInstance(activity)
//
//    private var successListener: SuccessListener? = null
//    private var errorListener: ErrorListener? = null
//    private var loadingListener: LoadingListener? = null
//
//    private var isVideoWatched = false
//
//    init {
//        rewardedVideoAd.rewardedVideoAdListener = this
//        activity.lifecycle.addObserver(this)
//    }
//
//    override fun loadAd() {
//        if (!rewardedVideoAd.isLoaded) {
//            rewardedVideoAd.loadAd(unitsId.rewardedAd(), AdRequest.Builder().build())
//        } else {
//            loadingListener?.invoke(true)
//        }
//    }
//
//    override fun showAd() {
//        if (rewardedVideoAd.isLoaded) {
//            rewardedVideoAd.show()
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
//    //RewardedVideoAdListener
//
//    override fun onRewardedVideoAdClosed() {
//        if (!isVideoWatched) {
//            errorListener?.invoke(-1)
//        }
//    }
//
//    override fun onRewardedVideoAdLeftApplication() {
//        errorListener?.invoke(-1)
//    }
//
//    override fun onRewardedVideoAdLoaded() {
//        loadingListener?.invoke(false)
//    }
//
//    override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {
//        successListener?.invoke()
//    }
//
//    override fun onRewarded(p0: RewardItem?) {
//        isVideoWatched = true
//        successListener?.invoke()
//    }
//
//    override fun onRewardedVideoStarted() {}
//
//    override fun onRewardedVideoCompleted() {}
//
//    override fun onRewardedVideoAdOpened() {}
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//    fun activityOnPause() {
//        rewardedVideoAd.pause(activity)
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    fun activityOnResume() {
//        rewardedVideoAd.resume(activity)
//    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//    fun activityOnDestroy() {
//        rewardedVideoAd.destroy(activity)
//    }
//}