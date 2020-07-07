package com.moreyeah.test.presentation.di.module

import com.moreyeah.test.data.remote.dataSource.MoreYeahApi
import com.moreyeah.test.domain.mapper.DataToDomainMapperImp
import com.moreyeah.test.data.repository.DomainRepoImp
import com.moreyeah.test.domain.mapper.DataToDomainMapper
import com.moreyeah.test.domain.repository.DomainRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton



@Module
class RepoModule {
    @Provides
    @Singleton
    internal fun provideMovieDataMapper(): DataToDomainMapper {
        return DataToDomainMapperImp()
    }

    @Provides
    @Singleton
    internal fun provideMovieRepository(moreYeahApi: MoreYeahApi,
                                        topicMapper: DataToDomainMapper): DomainRepo {
        return DomainRepoImp(moreYeahApi,topicMapper)
    }

}