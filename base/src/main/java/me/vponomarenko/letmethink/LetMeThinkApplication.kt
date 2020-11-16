package me.vponomarenko.letmethink

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import me.vponomarenko.letmethink.di.component.DaggerAppComponent
import timber.log.Timber
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 20/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class LetMeThinkApplication : Application(), HasActivityInjector {

    @Inject
    protected lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    protected lateinit var timberTree: Timber.Tree

    override fun onCreate() {
        super.onCreate()

//        FirebaseApp.initializeApp(this)

        DaggerAppComponent.builder()
                .context(this)
                .build()
                .inject(this)

        if (resources.getBoolean(R.bool.isInstantApp)) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(timberTree)
        }
    }

    override fun activityInjector() = dispatchingActivityInjector

}