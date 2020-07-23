package com.kodyuzz.kanas.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.kodyuzz.kanas.BuildConfig
import com.kodyuzz.kanas.InstagramApplication
import com.kodyuzz.kanas.data.local.db.DatabaseService
import com.kodyuzz.kanas.data.remote.NetworkService
import com.kodyuzz.kanas.data.remote.Networking
import com.kodyuzz.kanas.di.ApplicationContext
import com.kodyuzz.kanas.utils.common.FileUtils
import com.kodyuzz.kanas.utils.network.NetworkHelper
import com.kodyuzz.kanas.utils.network.NetworkHelperImp
import com.kodyuzz.kanas.utils.rx.RxSchedulerProvider
import com.kodyuzz.kanas.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: InstagramApplication) {

    @Provides
    @Singleton
    fun provideApplication(): Application = application

    @Provides
    @Singleton
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideTempDirectory() = FileUtils.getDirectory(application, "temp")


    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideSchedulerProvider(): SchedulerProvider = RxSchedulerProvider()

    @Provides
    @Singleton
    fun provideSharePreferences(): SharedPreferences =
        application.getSharedPreferences("kodyuzz-pref", Context.MODE_PRIVATE)


    @Provides
    @Singleton
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java, "kodyuzz-db"
        ).build()


    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            BuildConfig.API_KEY,
            BuildConfig.BASE_URL,
            application.cacheDir,
            10 * 1024 * 1024
        )

    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper= NetworkHelperImp(application)
}