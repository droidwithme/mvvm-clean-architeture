package com.moreyeah.test.presentation.ui.countrylist

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.moreyeah.test.R
import com.moreyeah.test.domain.mapper.Country
import com.moreyeah.test.domain.mapper.CountryEntity
import com.moreyeah.test.presentation.base.BaseFragment
import com.moreyeah.test.presentation.base.BaseViewState
import com.moreyeah.test.presentation.commons.GridSpacingItemDecoration
import com.moreyeah.test.presentation.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_topic.*
import java.util.*
import javax.inject.Inject


@Suppress("UNCHECKED_CAST")
class CountryFragment : BaseFragment<CountryFragmentViewModel>() {


    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mLinearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var mGridSpacingItemDecoration: GridSpacingItemDecoration

    @Inject
    lateinit var mCountryAdapter: CountryAdapter


    override fun getLayoutId(): Int = R.layout.fragment_detail
    override fun getViewModel(): CountryFragmentViewModel = ViewModelProviders.of(this, mViewModelFactory).get(CountryFragmentViewModel::class.java)
    override fun getLifeCycleOwner(): LifecycleOwner = this


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("CountyFragment", "onViewCreated()")
        setUp()
        getViewModel().getCountryList()
    }

    private fun setUp() {
        Log.e("CountyFragment", "setUp()")
        activity as MainActivity
        activity!!.toolbar_title.text = "CountryList"

        mLinearLayoutManager.isItemPrefetchEnabled = false
        countryRecyclerView.layoutManager = mLinearLayoutManager
        countryRecyclerView.addItemDecoration(mGridSpacingItemDecoration)
        countryRecyclerView.itemAnimator = DefaultItemAnimator()
        countryRecyclerView.adapter = mCountryAdapter

        val handler = Handler()
        val delay = 1000
        handler.postDelayed(object : Runnable {
            override fun run() {
                mCountryAdapter.notifyDataSetChanged()
                handler.postDelayed(this, delay.toLong())
            }
        }, delay.toLong())
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
                    it.data as CountryEntity
                    mCountryAdapter.addItems(it.data.countryList as ArrayList<Country>)
                }

            }
        })

    }
}