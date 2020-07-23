package com.kodyuzz.kanas.di.module

import androidx.lifecycle.LifecycleRegistry
import com.kodyuzz.kanas.di.ViewModelScope
import com.kodyuzz.kanas.ui.base.BaseItemVIewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemVIewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifeCycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}