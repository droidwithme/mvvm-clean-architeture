package com.moreyeah.test

import android.app.Activity
import android.app.Application
import android.util.Log
import com.moreyeah.test.presentation.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class DroidApp : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>




    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        Log.e("DroidApp", "nnCreate()")
        DaggerAppComponent.builder().application(this).build().inject(this)
    }


}