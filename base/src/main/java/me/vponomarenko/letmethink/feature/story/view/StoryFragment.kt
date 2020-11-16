package me.vponomarenko.letmethink.feature.story.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.annotation.Keep
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatDelegate
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_story.*
import me.vponomarenko.letmethink.R
import me.vponomarenko.letmethink.base.BaseFragment
import me.vponomarenko.letmethink.data.model.Story
import me.vponomarenko.letmethink.extension.observe
import me.vponomarenko.letmethink.extension.prepareToolbar
import me.vponomarenko.letmethink.extension.rxLoadImage
import me.vponomarenko.letmethink.extension.startCircularRevealAnimation
import me.vponomarenko.letmethink.feature.list.storysmall.StorySmallAdapter
import me.vponomarenko.letmethink.feature.story.viewdata.OtherStoriesViewState
import me.vponomarenko.letmethink.feature.story.viewdata.StoryViewState
import me.vponomarenko.letmethink.feature.story.viewmodel.StoryViewModel
import me.vponomarenko.letmethink.utils.NavigationDrawer
import me.vponomarenko.letmethink.utils.ViewModelFactory
import me.vponomarenko.letmethink.utils.filemanager.IImageManager
import javax.inject.Inject

/**
 * Author: Valery Ponomarenko
 * Date: 22/02/2018
 * LinkedIn: https://www.linkedin.com/in/ponomarenkovalery
 */

@Suppress("ProtectedInFinal")
@Keep
class StoryFragment : BaseFragment() {

    companion object {
        private const val EXTRA_STORY_ID = "me.vponomarenko.letmethink.feature.story.view.story_id"
        private const val MIN_DISTANCE = 150
        private const val REVEAL_ANIM_DURATION = 400L
        private const val REVEAL_START_RADIUS_DIVIDER = 3f

        fun newInstance(storyId: Int) =
            StoryFragment().apply {
                val args = Bundle()
                args.putInt(EXTRA_STORY_ID, storyId)
                this.arguments = args
            }
    }

    val storyId: Int
        get() = arguments?.getInt(EXTRA_STORY_ID) ?: throw Throwable("storyId is not found")

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory

    @Inject
    protected lateinit var storiesAdapter: StorySmallAdapter

    @Inject
    protected lateinit var imageManager: IImageManager

    @Inject
    protected lateinit var navigationDrawer: NavigationDrawer

    private var actionFavorite: MenuItem? = null
        set(value) {
            field = value
            value?.icon = fab_favorite.drawable
        }

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(StoryViewModel::class.java)
    }

    private var subscription: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(R.layout.fragment_story, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        prepareToolbar(toolbar, viewModel::onBackPressed)
        setHasOptionsMenu(true)
        prepareRecyclerView()
        observeViewModel()
        setListeners()
        navigationDrawer.lock()
//        view_ad.loadAd(AdRequest.Builder().build())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_story, menu)
        actionFavorite = menu.findItem(R.id.action_favorite)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.action_favorite -> onFavoriteClick().run { true }
            else -> super.onOptionsItemSelected(item)
        }

    override fun onPause() {
        super.onPause()
        subscription?.dispose()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        navigationDrawer.unlock()
    }

    private fun prepareRecyclerView() {
        with(rv_other_stories) {
            layoutManager = LinearLayoutManager(
                activity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = storiesAdapter
        }
    }

    private fun observeViewModel() {
        viewModel.story.observe(this) { storyViewData ->
            when (storyViewData) {
                is StoryViewState.StoryLoaded -> {
                    showStory(storyViewData.story)
                    showInstallAppDialog(storyViewData.showInstantAppDialog)
                }
            }
        }

        viewModel.otherStories.observe(this) { otherStoriesViewData ->
            when (otherStoriesViewData) {
                is OtherStoriesViewState.StoriesLoaded ->
                    storiesAdapter.stories = otherStoriesViewData.stories
            }
        }
    }

    private fun showStory(story: Story) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && image_story.tag == null) {
            image_story.startCircularRevealAnimation(
                image_story.width / 2,
                image_story.height,
                image_story.width / REVEAL_START_RADIUS_DIVIDER,
                image_story.width.toFloat(),
                REVEAL_ANIM_DURATION
            )
            image_story.tag = true
        }
        collapsing_toolbar.title = story.title
        text_story_title.text = story.title
        text_story_description.text = story.story
        expandable_answer.expandableText = story.answer
        story.imageName?.let {
            subscription = imageManager.rxLoadImage(context, it, image_story)
        }

        with(fab_favorite) {
            val favIcon = ContextCompat.getDrawable(
                context,
                if (story.isFavorite) R.drawable.ic_favorite_filled else R.drawable.ic_favorite
            )
            setImageDrawable(favIcon)
            actionFavorite?.icon = favIcon
            tag = story.isFavorite
        }
    }

    private fun showInstallAppDialog(show: Boolean) {
        if (!show) return

        AlertDialog.Builder(requireContext())
            .setTitle(R.string.install_app_title)
            .setMessage(R.string.wanna_install_app)
            .setPositiveButton(R.string.yes) { dialog, _ ->
                viewModel.onInstallAppClick()
                dialog.dismiss()
            }
            .setNegativeButton(R.string.later) { dialog, _ ->
                viewModel.onInstallAppLaterClick()
                dialog.dismiss()
            }
            .show()
    }

    private fun setListeners() {
        fab_favorite.setOnClickListener { onFavoriteClick() }

        storiesAdapter.setOnClickListener(viewModel::onStoryClick)

        expandable_answer.stateChangeListener = { viewModel.onAnswerWatcher() }

        bar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (appBarLayout.totalScrollRange - Math.abs(verticalOffset) < MIN_DISTANCE) {
                actionFavorite?.isVisible = true
            } else if (actionFavorite?.isVisible == true) {
                actionFavorite?.isVisible = false
            }
        }

//        view_ad.adListener = object : AdListener() {
//            override fun onAdLoaded() {
//                view_ad?.makeVisible()
//            }
//
//            override fun onAdClosed() {
//                view_ad?.makeGone()
//            }
//        }
    }

    private fun onFavoriteClick() {
        viewModel.onFavoriteClick((fab_favorite.tag as? Boolean)?.not() ?: false)
    }
}