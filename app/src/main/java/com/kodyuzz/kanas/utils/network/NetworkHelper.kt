package com.kodyuzz.kanas.utils.network

interface NetworkHelper{
    fun isNetworkConnected():Boolean

    fun castToNetworkError(throwable: Throwable):NetworkError
}