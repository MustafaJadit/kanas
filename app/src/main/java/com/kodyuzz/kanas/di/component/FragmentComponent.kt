package com.kodyuzz.kanas.di.component

import com.kodyuzz.kanas.di.FragmentScope
import com.kodyuzz.kanas.di.module.FragmentModule
import com.kodyuzz.kanas.ui.home.HomeFragment
import com.kodyuzz.kanas.ui.photo.PhotoFragment
import com.kodyuzz.kanas.ui.profile.ProfileFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(fragment: HomeFragment)

    fun inject(fragment: ProfileFragment)

    fun inject(fragment: PhotoFragment)

}