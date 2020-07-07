

package com.moreyeah.test.presentation.di.module

import com.moreyeah.test.presentation.ui.topic.TopicFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class TopicFragmentProvider {

    @ContributesAndroidInjector(modules =[(TopicFragmentModule::class)])
    internal abstract fun provideMainFragmentFactory(): TopicFragment

}