package me.vponomarenko.letmethink.feature.main.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.Keep
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_single_fragment.*
import me.vponomarenko.letmethink.Screens
import me.vponomarenko.letmethink.base.SingleFragmentActivity
import me.vponomarenko.letmethink.utils.NavigationDrawer
import javax.inject.Inject

@Suppress("ProtectedInFinal")
@Keep
class MainActivity : SingleFragmentActivity() {

    companion object {
        private const val EXTRA_FOR_UPDATE = "me.vponomarenko.letmethink.feature.main.view.update"

        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)

        fun newIntentForUpdate(context: Context): Intent =
                Intent(context, MainActivity::class.java)
                        .putExtra(EXTRA_FOR_UPDATE, true)

    }

    @Inject
    protected lateinit var navigationDrawer: NavigationDrawer

    override val startFragmentName = getScreenName()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationDrawer.setDrawer(nav_drawer, nav_view)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> navigationDrawer.openDrawer().run { true }
                else -> super.onOptionsItemSelected(item)
            }

    private fun getScreenName() =
            if (intent?.getBooleanExtra(EXTRA_FOR_UPDATE, false) == true)
                Screens.UPDATE_FRAGMENT
            else
                Screens.MAIN_FRAGMENT

}
