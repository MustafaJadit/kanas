package com.kodyuzz.kanas

import android.content.Context

open class Demo1(val myName: String,var context:Context) {
    fun getName(): String {
        return myName
    }

    init {
        println(context.packageName)
    }
}