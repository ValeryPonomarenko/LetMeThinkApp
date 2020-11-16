package me.vponomarenko.letmethink.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import me.vponomarenko.letmethink.LetMeThinkApplication
import me.vponomarenko.letmethink.di.module.ActivitiesModule
import me.vponomarenko.letmethink.di.module.DataSourcesModule
import me.vponomarenko.letmethink.di.module.DatabaseModule
import me.vponomarenko.letmethink.di.module.InteractorsModule
import me.vponomarenko.letmethink.di.module.LoggingModule
import me.vponomarenko.letmethink.di.module.RepositoriesModule
import me.vponomarenko.letmethink.di.module.RouterModule
import me.vponomarenko.letmethink.di.module.UtilsModule
import javax.inject.Singleton

/**
 * Author: Valery Ponomarenko
 * Date: 20/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivitiesModule::class,
        RouterModule::class,
        RepositoriesModule::class,
//        FirebaseModule::class,
        UtilsModule::class,
        DatabaseModule::class,
        InteractorsModule::class,
        LoggingModule::class,
        DataSourcesModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(app: LetMeThinkApplication)
}