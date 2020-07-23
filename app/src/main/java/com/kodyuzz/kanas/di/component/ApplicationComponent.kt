package com.kodyuzz.kanas.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.kodyuzz.kanas.InstagramApplication
import com.kodyuzz.kanas.data.local.db.DatabaseService
import com.kodyuzz.kanas.data.remote.NetworkService
import com.kodyuzz.kanas.data.repository.UserRepository
import com.kodyuzz.kanas.di.ApplicationContext
import com.kodyuzz.kanas.di.module.ApplicationModule
import com.kodyuzz.kanas.utils.network.NetworkHelper
import com.kodyuzz.kanas.utils.rx.SchedulerProvider
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import java.io.File
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(app: InstagramApplication)

    fun getApplication(): Application

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getDatabaseService(): DatabaseService

    fun getSharedPreferences(): SharedPreferences

    fun getNetworkHelper(): NetworkHelper

    fun getUserRepository(): UserRepository

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

    fun getTempDirectory(): File

}