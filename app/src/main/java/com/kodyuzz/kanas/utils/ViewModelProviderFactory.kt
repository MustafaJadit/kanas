package com.kodyuzz.kanas.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class ViewModelProviderFactory<T : ViewModel>(
    private val kclass: KClass<T>,
    private val creator: () -> T
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelCLass: Class<T>): T {
        if (modelCLass.isAssignableFrom(kclass.java)) return creator() as T
        throw IllegalArgumentException("Unknown class name")
    }

}
