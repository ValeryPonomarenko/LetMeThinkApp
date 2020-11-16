package me.vponomarenko.letmethink.feature.main.view

import android.os.Bundle
import android.support.annotation.Keep
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*
import me.vponomarenko.letmethink.base.BaseFragment
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.extension.makeGone
import me.vponomarenko.letmethink.extension.makeVisible
import me.vponomarenko.letmethink.extension.observe
import me.vponomarenko.letmethink.extension.prepareDrawer
import me.vponomarenko.letmethink.extension.provideViewModel
import me.vponomarenko.letmethink.feature.list.StoriesFeedAdapter
import me.vponomarenko.letmethink.feature.main.util.FiltersMenuAnimator
import me.vponomarenko.letmethink.feature.main.viewdata.MainViewState
import me.vponomarenko.letmethink.feature.main.viewmodel.MainViewModel
import me.vponomarenko.letmethink.utils.ViewModelFactory
import me.vponomarenko.letmethink.utils.searchengine.FabScrollingHelper
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 20/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Suppress("ProtectedInFinal")
@Keep
class MainFragment : BaseFragment() {

    companion object {
        private const val EXTRA_IS_FOR_PARTY =
            "me.vponomarenko.letmethink.feature.main.view.for_party"

        fun newInstance(isForParty: Boolean = false) =
            MainFragment().apply {
                arguments = Bundle().apply { putBoolean(EXTRA_IS_FOR_PARTY, isForParty) }
            }
    }

    @Inject
    protected lateinit var storiesAdapter: StoriesFeedAdapter

    @Inject
    protected lateinit var fabScrollingHelper: FabScrollingHelper

    @Inject
    protected lateinit var filtersMenuAnimator: FiltersMenuAnimator

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by provideViewModel<MainViewModel> { viewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareRecyclerView()
        prepareDrawer(toolbar)
        setListeners()
        with(viewModel.screenConfig) {
            toolbar.title = screenTitle
            view_filters.setFilters(sortingTypes)
        }
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        fabScrollingHelper.install(fab_filter, view_filters)
    }

    override fun onPause() {
        super.onPause()
        fabScrollingHelper.release()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.action_search -> {
                val view = view?.findViewById<View>(R.id.action_search)
                val cx = toolbar.width - (view?.right?.minus(view.width / 2) ?: 0)
                val cy = view?.top?.plus(view.height / 2) ?: 0
                viewModel.onSearchClick(cx, cy).run { true }
            }
            else -> super.onOptionsItemSelected(item)
        }

    fun isForParty() = arguments?.getBoolean(EXTRA_IS_FOR_PARTY) ?: false

    private fun prepareRecyclerView() {
        with(rv_stories) {
            layoutManager = LinearLayoutManager(activity)
            storiesAdapter.setOnClickListener { viewModel.onStoryClick(it) }
            adapter = storiesAdapter
        }
    }

    private fun setListeners() {
        viewModel.stories.observe(this) {
            when (it) {
                is MainViewState.Loading -> view_update_progress.makeVisible()
                is MainViewState.Loaded -> {
                    storiesAdapter.items = it.stories
                    view_update_progress.makeGone()
                }
            }
        }

        fab_filter.setOnClickListener { filtersMenuAnimator.show(fab_filter, view_filters) }
        view_filters.onClickListener = {
            filtersMenuAnimator.hide(fab_filter, view_filters)
            viewModel.onFilterSelected(it)
        }

        rv_stories.addOnScrollListener(fabScrollingHelper)
    }
}