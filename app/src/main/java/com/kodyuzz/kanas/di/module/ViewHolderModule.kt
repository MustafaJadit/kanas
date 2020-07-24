package com.kodyuzz.kanas.di.module

import androidx.lifecycle.LifecycleRegistry
import com.kodyuzz.kanas.di.ViewModelScope
import com.kodyuzz.kanas.ui.base.BaseItemViewHolder
import dagger.Module
import dagger.Provides

@Module
class ViewHolderModule(private val viewHolder: BaseItemViewHolder<*, *>) {

    @Provides
    @ViewModelScope
    fun provideLifecycleRegistry(): LifecycleRegistry = LifecycleRegistry(viewHolder)
}