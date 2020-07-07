package com.moreyeah.test.presentation.base

sealed class BaseViewState {
    class loading(val isVisible: Boolean) : BaseViewState()
    class hasData<T : Any>(val data: T) : BaseViewState()
    class errorText(val text: String) : BaseViewState()
    class errorRes(val resId: Int) : BaseViewState()
    class messageText(val text: String) : BaseViewState()
    class messageRes(val resId: Int) : BaseViewState()
}

sealed class DetailViewState : BaseViewState() {
    class errorText(val text: String) : DetailViewState()
    class messageRes(val resId: Int) : DetailViewState()

}

