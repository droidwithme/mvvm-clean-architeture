

package com.moreyeah.test.presentation.di.module

import com.moreyeah.test.presentation.commons.ViewModelProviderFactory
import com.moreyeah.test.data.repository.DomainRepoImp
import com.moreyeah.test.domain.useCase.GetTopic
import com.moreyeah.test.presentation.ui.topic.TopicAdapter
import com.moreyeah.test.presentation.ui.topic.TopicFragment
import com.moreyeah.test.presentation.ui.topic.TopicFragmentViewModel
import com.moreyeah.test.presentation.commons.GridSpacingItemDecoration
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides


@Module
class TopicFragmentModule {

    @Provides
    internal fun getMoviesProvider(repository: DomainRepoImp): GetTopic {
        return GetTopic(repository)
    }


    @Provides
    internal fun provideMainFragmentViewModel(getMovies: GetTopic): TopicFragmentViewModel {
        return TopicFragmentViewModel(getMovies)
    }

    @Provides
    internal fun provideLinearLayoutManager(fragment: TopicFragment): LinearLayoutManager {
        return LinearLayoutManager((fragment.activity as Context?)!!)
    }

    @Provides
    internal fun provideGridSpacingItemDecoration(): GridSpacingItemDecoration {
        return GridSpacingItemDecoration(2, 5, true)
    }

    @Provides
    internal fun provideMovieAdapter(context: Context): TopicAdapter {
        return TopicAdapter(ArrayList(),context)
    }


    @Provides
    internal fun mainFragmentViewModelProvider(topicFragmentViewModel: TopicFragmentViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(topicFragmentViewModel)
    }

}