package com.kodyuzz.kanas.di.component

import com.kodyuzz.kanas.di.ActivityScope
import com.kodyuzz.kanas.di.module.ActivityModule
import com.kodyuzz.kanas.ui.login.LoginActivity
import com.kodyuzz.kanas.ui.main.MainActivity
import com.kodyuzz.kanas.ui.splash.SplashActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: LoginActivity)

    fun inject(activity: SplashActivity)

}