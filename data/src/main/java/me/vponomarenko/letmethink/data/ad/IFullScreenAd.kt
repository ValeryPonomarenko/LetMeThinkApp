package me.vponomarenko.letmethink.data.ad

/**
 * Author: Valery Ponomarenko
 * Date: 11/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

internal typealias SuccessListener = () -> Unit
internal typealias ErrorListener = (ErrorCode: Int) -> Unit
internal typealias LoadingListener = (isLoading: Boolean) -> Unit

interface IFullScreenAd {

    fun loadAd()

    fun showAd()

    fun onSuccessListener(listener: SuccessListener)

    fun onErrorListener(listener: ErrorListener)

    /**
     * Returns true if the ad is loading, false otherwise
     */
    fun onLoadingListener(listener: LoadingListener)

}