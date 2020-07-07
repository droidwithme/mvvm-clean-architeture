package com.moreyeah.test.presentation.di.module

import com.moreyeah.test.data.repository.DomainRepoImp
import com.moreyeah.test.presentation.main.MainViewModel
import dagger.Module
import dagger.Provides



@Module
class MainActivityModule {

    @Provides
    internal fun provideMainViewModel(movieRepository: DomainRepoImp): MainViewModel {
        return MainViewModel(movieRepository)
    }

}