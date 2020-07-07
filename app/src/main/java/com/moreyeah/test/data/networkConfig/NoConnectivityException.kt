package com.moreyeah.test.data.networkConfig

import java.io.IOException

class NoConnectivityException : IOException() {

    // You can send any message whatever you want from here.
    override val message: String
        get() = "No Internet Connection"
}