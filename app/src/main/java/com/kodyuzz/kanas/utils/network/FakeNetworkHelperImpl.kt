package com.kodyuzz.kanas.utils.network

import android.content.Context

class FakeNetworkHelperImpl constructor(private val context: Context) : NetworkHelper {
    override fun isNetworkConnected(): Boolean {
        return true
    }

    override fun castToNetworkError(throwable: Throwable): NetworkError {
        return NetworkHelperImp(context).castToNetworkError(throwable)
    }

}