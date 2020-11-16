package me.vponomarenko.letmethink.base

import android.content.Context
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection

/**
 * Author: Valery Ponomarenko
 * Date: 20/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

abstract class BaseFragment : Fragment() {

//    @Inject
//    protected lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onResume() {
        super.onResume()
//        firebaseAnalytics.setCurrentScreen(
//            requireActivity(),
//            javaClass.simpleName,
//            javaClass.simpleName
//        )
    }
}