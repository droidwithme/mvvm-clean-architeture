package com.moreyeah.test.presentation.ui.topic

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.moreyeah.test.R
import com.moreyeah.test.domain.mapper.Phrase
import com.moreyeah.test.domain.mapper.TopicEntity
import com.moreyeah.test.presentation.base.BaseFragment
import com.moreyeah.test.presentation.base.BaseViewState
import com.moreyeah.test.presentation.commons.GridSpacingItemDecoration
import com.moreyeah.test.presentation.main.MainActivity
import com.moreyeah.test.presentation.ui.countrylist.CountryFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_topic.*
import javax.inject.Inject



/*
* To list the topics
* */
@Suppress("UNCHECKED_CAST")
class TopicFragment : BaseFragment<TopicFragmentViewModel>(), TopicAdapter.OnTopicClickListener {


    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mLinearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var mGridSpacingItemDecoration: GridSpacingItemDecoration

    @Inject
    lateinit var mTopicAdapter: TopicAdapter


    override fun getLayoutId(): Int = R.layout.fragment_topic
    override fun getViewModel(): TopicFragmentViewModel = ViewModelProviders.of(this, mViewModelFactory).get(TopicFragmentViewModel::class.java)
    override fun getLifeCycleOwner(): LifecycleOwner = this


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("CountyFragment", "onViewCreated()")
        setUp()
        getViewModel().getTopics()
    }

    private fun setUp() {
        Log.e("CountyFragment", "setUp()")
        activity as MainActivity
        activity!!.toolbar_title.text = "TaskHuman"

        mLinearLayoutManager.isItemPrefetchEnabled = false
        moviesRecycler.layoutManager = mLinearLayoutManager
        moviesRecycler.addItemDecoration(mGridSpacingItemDecoration)
        moviesRecycler.itemAnimator = DefaultItemAnimator()
        moviesRecycler.adapter = mTopicAdapter
        mTopicAdapter.setListener(this)
        observeViewState()
    }


    private fun observeViewState() {
        Log.e("CountyFragment", "observeViewState()")
        getViewModel().uiState.observe(this, Observer {
            hideLoading()
            when (it) {
                is BaseViewState.messageText ->
                    showMessage(it.text)
                is BaseViewState.loading ->
                    showLoading()
                is BaseViewState.errorText ->
                    onError(it.text)
                is BaseViewState.hasData<*> -> {
                    it.data as ArrayList<TopicEntity>?
                    val topicArrayList = ArrayList<Phrase>()
                    for (phrase in it.data) {
                        topicArrayList.addAll(phrase.phrase)
                    }
                    mTopicAdapter.addItems(topicArrayList)
                }

            }
        })
    }

    override fun onItemClick() {
        val fragmentManager: FragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
        fragmentTransaction.replace(R.id.container, CountryFragment(), "country")
                .addToBackStack(null).commit()

    }


}