package com.kodyuzz.kanas.utils.log

import com.kodyuzz.kanas.BuildConfig
import timber.log.Timber

object Logger {

    init {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

    fun d(tag: String, s: String, vararg params: Any) =
        Timber.tag(tag).d(s, params)

    fun e(tag:String, s :String, vararg params : Any)=
        Timber.tag(tag).e(s,params)
}