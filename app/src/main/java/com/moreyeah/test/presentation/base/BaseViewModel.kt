package com.moreyeah.test.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



abstract class BaseViewModel<T : BaseViewState>() : ViewModel() {

    protected open var mUiState = MutableLiveData<T>()
    open val uiState: LiveData<T> = mUiState

}