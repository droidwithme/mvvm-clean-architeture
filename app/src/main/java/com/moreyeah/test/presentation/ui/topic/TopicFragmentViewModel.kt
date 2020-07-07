package com.moreyeah.test.presentation.ui.topic

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.moreyeah.test.domain.mapper.TopicEntity
import com.moreyeah.test.domain.useCase.GetTopic
import com.moreyeah.test.presentation.base.BaseViewModel
import com.moreyeah.test.presentation.base.BaseViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TopicFragmentViewModel @Inject constructor(val getTopics: GetTopic) : BaseViewModel<BaseViewState>() {


    fun getTopics() {
        Log.e("CountryFragmentViewModel", "getTopics()")
        mUiState.value = BaseViewState.loading(true)
        viewModelScope.launch(Dispatchers.IO) {
            getTopics.invoke().collect {
                withContext(Dispatchers.Main) { updateUI(it) }
            }
        }
    }

    private fun updateUI(it: List<TopicEntity>?) {
        Log.e("CountryFragmentViewModel", "updateUI($it)")
        if (!it.isNullOrEmpty())
            mUiState.postValue(BaseViewState.hasData(it))
        else
            mUiState.postValue(BaseViewState.errorText("No topic found"))
    }


}