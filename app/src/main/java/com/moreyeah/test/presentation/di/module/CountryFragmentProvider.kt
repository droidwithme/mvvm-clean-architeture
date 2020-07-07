

package com.moreyeah.test.presentation.di.module

import com.moreyeah.test.presentation.ui.countrylist.CountryFragment
import com.moreyeah.test.presentation.ui.topic.TopicFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class CountryFragmentProvider {

    @ContributesAndroidInjector(modules =[(CountryFragmentModule::class)])
    internal abstract fun provideMainFragmentFactory(): CountryFragment

}