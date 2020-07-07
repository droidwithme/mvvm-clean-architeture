

package com.moreyeah.test.presentation.di.builder

import com.moreyeah.test.presentation.di.module.CountryFragmentProvider
import com.moreyeah.test.presentation.di.module.TopicFragmentProvider
import com.moreyeah.test.presentation.di.module.MainActivityModule
import com.moreyeah.test.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class), (TopicFragmentProvider::class), (CountryFragmentProvider::class)])
    internal abstract fun bindMainActivity(): MainActivity

}