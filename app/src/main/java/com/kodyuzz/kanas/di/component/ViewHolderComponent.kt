package com.kodyuzz.kanas.di.component

import com.kodyuzz.kanas.di.ViewModelScope
import com.kodyuzz.kanas.di.module.ViewHolderModule
import com.kodyuzz.kanas.ui.dummies.DummyItemViewHolder
import com.kodyuzz.kanas.ui.home.posts.PostItemViewHolder
import dagger.Component

@ViewModelScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewHolderModule::class]
)
interface ViewHolderComponent {

    fun inject(viewHolder: DummyItemViewHolder)

    fun inject(viewHolder: PostItemViewHolder)
}