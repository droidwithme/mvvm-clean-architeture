package com.moreyeah.test.presentation.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moreyeah.test.data.repository.DomainRepoImp
import com.moreyeah.test.domain.useCase.GetCountryList
import com.moreyeah.test.presentation.commons.GridSpacingItemDecoration
import com.moreyeah.test.presentation.commons.ViewModelProviderFactory
import com.moreyeah.test.presentation.ui.countrylist.CountryAdapter
import com.moreyeah.test.presentation.ui.countrylist.CountryFragment
import com.moreyeah.test.presentation.ui.countrylist.CountryFragmentViewModel
import dagger.Module
import dagger.Provides


@Module
class CountryFragmentModule {

    @Provides
    internal fun getCountryList(repository: DomainRepoImp): GetCountryList {
        return GetCountryList(repository)
    }


    @Provides
    internal fun provideMainFragmentViewModel(getCountryList: GetCountryList): CountryFragmentViewModel {
        return CountryFragmentViewModel(getCountryList)
    }

    @Provides
    internal fun provideLinearLayoutManager(fragment: CountryFragment): LinearLayoutManager {
        return LinearLayoutManager((fragment.activity as Context?)!!)
    }

    @Provides
    internal fun provideGridSpacingItemDecoration(): GridSpacingItemDecoration {
        return GridSpacingItemDecoration(2, 5, true)
    }

    @Provides
    internal fun provideMovieAdapter(context: Context): CountryAdapter {
        return CountryAdapter(ArrayList(), context)
    }


    @Provides
    internal fun mainFragmentViewModelProvider(countryFragmentViewModel: CountryFragmentViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(countryFragmentViewModel)
    }

}