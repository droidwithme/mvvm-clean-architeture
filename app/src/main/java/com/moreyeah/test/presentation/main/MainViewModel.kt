package com.moreyeah.test.presentation.main

import com.moreyeah.test.data.repository.DomainRepoImp
import com.moreyeah.test.presentation.base.BaseViewModel
import com.moreyeah.test.presentation.base.BaseViewState
import javax.inject.Inject

class MainViewModel @Inject constructor(movieRepository: DomainRepoImp) : BaseViewModel<BaseViewState>() {


}