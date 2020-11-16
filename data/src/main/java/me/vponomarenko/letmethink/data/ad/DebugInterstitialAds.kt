package me.vponomarenko.letmethink.data.ad

//import com.google.android.gms.ads.AdListener
//import com.google.android.gms.ads.AdRequest
//import com.google.android.gms.ads.InterstitialAd
import android.arch.lifecycle.LifecycleObserver
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 11/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class DebugInterstitialAds @Inject constructor(
    activity: AppCompatActivity
) : IFullScreenAd, LifecycleObserver {
    override fun loadAd() {
        
    }

    override fun showAd() {
        
    }

    override fun onSuccessListener(listener: SuccessListener) {
        listener()
    }

    override fun onErrorListener(listener: ErrorListener) {
        
    }

    override fun onLoadingListener(listener: LoadingListener) {
        
    }
}