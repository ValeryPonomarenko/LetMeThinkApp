package me.vponomarenko.letmethink.feature.splash.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_splash.*
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.base.BaseFragment
import me.vponomarenko.letmethink.extension.makeGone
import me.vponomarenko.letmethink.extension.makeVisible
import me.vponomarenko.letmethink.extension.observe
import me.vponomarenko.letmethink.extension.provideViewModel
import me.vponomarenko.letmethink.feature.splash.viewdata.SplashViewState
import me.vponomarenko.letmethink.feature.splash.viewmodel.SplashViewModel
import me.vponomarenko.letmethink.utils.ViewModelFactory
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 10/06/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Suppress("ProtectedInFinal")
class SplashFragment : BaseFragment() {

    companion object {
        fun newInstance() = SplashFragment()
    }

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by provideViewModel<SplashViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_splash, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(this) {
            when (it) {
                is SplashViewState.Empty -> {
                    progress_bar.makeGone()
                    text_hint.makeGone()
                    button_retry.makeGone()
                }
                is SplashViewState.Error -> {
                    progress_bar.makeGone()
                    text_hint.makeVisible()
                    text_hint.text = it.message
                    button_retry.makeVisible()
                }
                is SplashViewState.LoadingData -> {
                    progress_bar.makeVisible()
                    text_hint.makeVisible()
                    text_hint.text = it.message
                    button_retry.makeGone()
                }
            }
        }

        button_retry.setOnClickListener { viewModel.startLoading() }
    }
}