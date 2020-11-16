package me.vponomarenko.letmethink.feature.search.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.Keep
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.fragment_search.*
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.base.BaseFragment
import me.vponomarenko.letmethink.extension.*
import me.vponomarenko.letmethink.feature.list.StoriesFeedAdapter
import me.vponomarenko.letmethink.feature.search.viewdata.SearchViewState
import me.vponomarenko.letmethink.feature.search.viewdata.SearchViewTransitionData
import me.vponomarenko.letmethink.feature.search.viewmodel.SearchViewModel
import me.vponomarenko.letmethink.utils.NavigationDrawer
import me.vponomarenko.letmethink.utils.ViewModelFactory
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 23/03/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Suppress("ProtectedInFinal")
@Keep
class SearchFragment : BaseFragment() {

    companion object {
        private const val EXTRA_TRANSITION_DATA = "me.vponomarenko.letmethink.feature.search.view.data"

        fun newInstance(data: SearchViewTransitionData) =
                SearchFragment().apply {
                    val args = Bundle()
                    args.putParcelable(EXTRA_TRANSITION_DATA, data)

                    arguments = args
                }
    }

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    @Inject
    protected lateinit var navigationDrawer: NavigationDrawer

    @Inject
    protected lateinit var storiesAdapter: StoriesFeedAdapter

    private val transitionData by lazy {
        arguments?.getParcelable<SearchViewTransitionData>(EXTRA_TRANSITION_DATA)
                ?: throw Throwable("There is no arg or it can't be cast to SearchViewTransitionData")
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_search, container, false).apply {
                appearWithCircularReveal(transitionData.revealStartX, transitionData.revealStartY)
                context?.let {
                    startColorAnimation(
                            ContextCompat.getColor(it, R.color.colorDarkGrey),
                            ContextCompat.getColor(it, R.color.colorGrey)
                    )
                }
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        with (rv_stories) {
            layoutManager = LinearLayoutManager(activity)
            storiesAdapter.setOnClickListener {
                viewModel.onStoryClick(it)
                hideKeyboard()
            }
            adapter = storiesAdapter
        }
        observeViewModel()
        editText_search.requestFocus()
        showKeyboard()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        navigationDrawer.unlock()
    }

    private fun setListeners() {
        button_back.setOnClickListener {
            hideKeyboard()
            viewModel.onBackPressed()
        }
        button_search.setOnClickListener {
            onSearchClick()
        }
        editText_search.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                onSearchClick()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
    }

    private fun observeViewModel() {
        viewModel.search.observe(this) {
            when (it) {
                is SearchViewState.Result -> {
                    view_empty.makeGone()
                    if (it.isLoading) {
                        progress_bar.makeVisible()
                    } else {
                        progress_bar.makeGone()
                    }
                    storiesAdapter.items = it.result
                }
                is SearchViewState.Empty -> {
                    progress_bar.makeGone()
                    view_empty.makeVisible()
                }
            }
            hideKeyboard()
        }
    }

    private fun onSearchClick() {
        viewModel.search(
                editText_search.text.toString(),
                switch_favorite.isChecked,
                switch_watched.isChecked
        )
        hideKeyboard()
    }

}