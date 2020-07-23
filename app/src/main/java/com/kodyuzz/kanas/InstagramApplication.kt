package com.kodyuzz.kanas

import android.app.Application
import com.kodyuzz.kanas.di.component.ApplicationComponent
import com.kodyuzz.kanas.di.component.DaggerApplicationComponent
import com.kodyuzz.kanas.di.module.ApplicationModule

class InstagramApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

    fun setComponent(applicationComponent: ApplicationComponent) {
        this.applicationComponent = applicationComponent
    }
}