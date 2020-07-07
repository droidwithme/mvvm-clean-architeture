package com.moreyeah.test.presentation.di.component

import com.moreyeah.test.DroidApp
import com.moreyeah.test.presentation.di.builder.ActivityBuilder
import com.moreyeah.test.presentation.di.module.*
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton



@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class),
    (NetworkModule::class), (RepoModule::class), (ActivityBuilder::class)])

interface AppComponent {

    fun inject(app: DroidApp)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}