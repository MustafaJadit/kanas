package com.kodyuzz.kanas.di.component

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.kodyuzz.kanas.InstagramApplication
import com.kodyuzz.kanas.data.local.db.DatabaseService
import com.kodyuzz.kanas.data.remote.NetworkService
import com.kodyuzz.kanas.data.repository.UserRepository
import com.kodyuzz.kanas.di.ApplicationContext
import com.kodyuzz.kanas.di.TempDirectory
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

    /**
     * These methods are written in ApplicationComponent because the instance of
     * NetworkService is singleton and is maintained in the ApplicationComponent's implementation by Dagger
     * For NetworkService singleton instance to be accessible to say DummyActivity's DummyViewModel
     * this ApplicationComponent must expose a method that returns NetworkService instance
     * This method will be called when NetworkService is injected in DummyViewModel.
     * Also, in ActivityComponent you can find dependencies = [ApplicationComponent::class] to link this relationship
     */
    fun getNetworkService(): NetworkService

    fun getDatabaseService(): DatabaseService

    fun getSharedPreferences(): SharedPreferences

    fun getNetworkHelper(): NetworkHelper

    /**---------------------------------------------------------------------------
     * Dagger will internally create UserRepository instance using constructor injection.
     * Dependency through constructor
     * UserRepository ->
     *  [NetworkService -> Nothing is required],
     *  [DatabaseService -> Nothing is required],
     *  [UserPreferences -> [SharedPreferences -> provided by the function provideSharedPreferences in ApplicationModule class]]
     * So, Dagger will be able to create an instance of UserRepository by its own using constructor injection
     *---------------------------------------------------------------------------------
     */
    fun getUserRepository(): UserRepository

    fun getSchedulerProvider(): SchedulerProvider

    fun getCompositeDisposable(): CompositeDisposable

    @TempDirectory
    fun getTempDirectory(): File
}