package me.vponomarenko.letmethink.interactor.rateapp

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import me.vponomarenko.letmethink.Screens
import me.vponomarenko.letmethink.core.data.rateapp.RateApp
import me.vponomarenko.letmethink.data.model.User
import me.vponomarenko.letmethink.data.repository.IUserRepository
import me.vponomarenko.letmethink.utils.analytics.IAnalyticsManager
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 24/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

class RateAppInteractor @Inject constructor(
    private val router: Router,
    private val userRepository: IUserRepository,
    private val analyticsManager: IAnalyticsManager
) : IRateAppInteractor {

    companion object {
        private const val RUNS_TO_SHOW_RATE = 2
    }

    private var emitter: ObservableEmitter<RateApp>? = null

    override fun getRateApp(): Flowable<RateApp> =
        Observable
            .create<RateApp> {
                emitter = it
                it.setCancellable { emitter = null }
                it.onNext(createRateAppObject())
            }
            .toFlowable(BackpressureStrategy.LATEST)

    override fun onRateAppClick(id: Int): Boolean {
        var user = createUser()
        when (id) {
            ERateApp.Now.id -> {
                router.navigateTo(Screens.PLAY_MARKET)
                user = createUser { copy(appRated = true) }
            }
            ERateApp.Later.id -> user = createUser { copy(runsApp = 0) }
            ERateApp.Never.id -> user = createUser { copy(appRated = true) }
        }
        userRepository.updateUser(user)
        emitter?.onNext(createRateAppObject())

        return getRateAppType(id).run {
            if (this == null) return@run false

            analyticsManager.logRateApp(this)
            true
        }
    }

    override fun updateRateAppState() {
        userRepository.updateUser(createUser())
    }

    private fun createRateAppObject() = RateApp(
        ERateApp.Now.id,
        ERateApp.Later.id,
        ERateApp.Never.id,
        userRepository.getUser().run { !firstStart && !appRated && runsApp > RUNS_TO_SHOW_RATE }
    )

    private fun createUser(extra: (User.() -> User)? = null): User {
        var user = userRepository.getUser()
        if (user.appRated) return user

        if (user.firstStart) {
            user = user.copy(firstStart = false)
        } else if (user.runsApp <= RUNS_TO_SHOW_RATE) {
            user = user.copy(runsApp = user.runsApp + 1)
        }

        extra?.let {
            user = extra(user)
        }
        return user
    }

    private fun getRateAppType(id: Int) = when (id) {
        ERateApp.Now.id -> ERateApp.Now
        ERateApp.Later.id -> ERateApp.Later
        ERateApp.Never.id -> ERateApp.Never
        else -> null
    }
}