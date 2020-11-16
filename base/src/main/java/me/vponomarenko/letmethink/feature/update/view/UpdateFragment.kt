package me.vponomarenko.letmethink.feature.update.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.Keep
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.partial_error.*
import kotlinx.android.synthetic.main.partial_success.*
import kotlinx.android.synthetic.main.partial_update_progress.*
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.base.BaseFragment
import me.vponomarenko.letmethink.extension.alert
import me.vponomarenko.letmethink.extension.makeGone
import me.vponomarenko.letmethink.extension.makeVisible
import me.vponomarenko.letmethink.extension.observe
import me.vponomarenko.letmethink.extension.prepareDrawer
import me.vponomarenko.letmethink.feature.update.viewdata.UpdateViewState
import me.vponomarenko.letmethink.feature.update.viewmodel.UpdateViewModel
import me.vponomarenko.letmethink.utils.ViewModelFactory
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 17/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Suppress("ProtectedInFinal")
@Keep
class UpdateFragment : BaseFragment() {

    companion object {
        fun newInstance() = UpdateFragment()
    }

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(UpdateViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_update, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareDrawer(toolbar)
        toolbar.title = getString(R.string.title_updates)
        observeViewModel()
        setListeners()
    }

    private fun observeViewModel() {
        viewModel.updateViewState.observe(this) {
            when (it) {
                is UpdateViewState.Start -> {
                    view_success.makeGone()
                    view_error.makeGone()
                }
                is UpdateViewState.Updating -> {
                    view_error.makeGone()
                    update_text_update_hint.text = it.message
                    view_update_progress.makeVisible()
                }
                is UpdateViewState.Done -> {
                    view_update_progress.makeGone()
                    view_success.makeVisible()
                }
                is UpdateViewState.Error -> {
                    view_update_progress.makeGone()
                    view_error.makeVisible()
                }
            }
        }
    }

    private fun setListeners() {
        button_update.setOnClickListener {
            showAdDialog()
        }
        button_ok.setOnClickListener {
            viewModel.onOkClick()
        }
        button_retry.setOnClickListener {
            showAdDialog()
        }
        button_close.setOnClickListener {
            viewModel.onOkClick()
        }
    }

    private fun showAdDialog() {
        alert(
            title = R.string.title_updates,
            message = R.string.watch_ad_to_update,
            okButtonText = R.string.watch,
            cancelButtonText = R.string.cancel,
            onSuccess = viewModel::showAd
        )
    }
}