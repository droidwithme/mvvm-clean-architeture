package com.moreyeah.test.presentation.main

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.moreyeah.test.R
import com.moreyeah.test.presentation.base.BaseActivity
import com.moreyeah.test.presentation.ui.topic.TopicFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity<MainViewModel>(), HasSupportFragmentInjector {

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mMainViewModel: MainViewModel

    private lateinit var mContext: Context
    private var doubleBackToExitPressedOnce = false
    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getViewModel(): MainViewModel = mMainViewModel


    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setUp()
        Log.e("MainActivity", "onCreate()")
    }

    private fun setUp() {
        setSupportActionBar(toolbar)
        Log.e("MainActivity", "setUp()")
        val mMainFragment = TopicFragment()
        replaceFragment(mMainFragment)
    }


    private fun replaceFragment(fragment: Fragment) {
        Log.e("MainActivity", "replaceFragment()")
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
        fragmentTransaction.replace(R.id.container, fragment, "topic")
                .addToBackStack(null).commit()

    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.container)
        if (fragment is TopicFragment) {
            if (doubleBackToExitPressedOnce) {
                this.finish()
            } else {
                this.doubleBackToExitPressedOnce = true
                onError("Please click Back again to exit")
                Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
            }

        } else {
            setUp()
        }


    }


}

