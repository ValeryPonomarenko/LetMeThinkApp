package me.vponomarenko.letmethink.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.routing.base.INavigatorBinder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 20/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

abstract class SingleFragmentActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    protected lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    protected lateinit var navigatorBinder: INavigatorBinder

    @Inject
    protected lateinit var router: Router

    protected abstract val startFragmentName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_fragment)

//        MobileAds.initialize(this, BuildConfig.ADMOB_ID)

        if (savedInstanceState == null) {
            router.replaceScreen(startFragmentName)
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorBinder.bind()
    }

    override fun onPause() {
        super.onPause()
        navigatorBinder.unbind()
    }

    override fun supportFragmentInjector() = fragmentInjector

}