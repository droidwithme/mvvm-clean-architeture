package com.moreyeah.test.presentation.ui.countrylist

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.moreyeah.test.domain.mapper.CountryEntity
import com.moreyeah.test.domain.useCase.GetCountryList
import com.moreyeah.test.presentation.base.BaseViewModel
import com.moreyeah.test.presentation.base.BaseViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CountryFragmentViewModel @Inject constructor(val getCountryList: GetCountryList) : BaseViewModel<BaseViewState>() {


    fun getCountryList() {
        Log.e("CountryFragmentViewModel", "getTopics()")
        mUiState.value = BaseViewState.loading(true)
        viewModelScope.launch(Dispatchers.IO) {
            getCountryList.invoke().collect {
                withContext(Dispatchers.Main) { updateUI(it) }
            }
        }
    }

    private fun updateUI(it: CountryEntity?) {
        Log.e("CountryFragmentViewModel", "updateUI($it)")
        if (it != null)
            mUiState.postValue(BaseViewState.hasData(it))
        else
            mUiState.postValue(BaseViewState.errorText("No country found"))
    }


}