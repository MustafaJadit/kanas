package com.kodyuzz.kanas.di.module

import android.content.Context
import androidx.room.Room
import com.kodyuzz.kanas.InstagramApplication
import com.kodyuzz.kanas.data.local.db.DatabaseService
import com.kodyuzz.kanas.data.remote.FakeNetworkService
import com.kodyuzz.kanas.data.remote.NetworkService
import com.kodyuzz.kanas.data.remote.Networking
import com.kodyuzz.kanas.di.ApplicationContext
import com.kodyuzz.kanas.utils.common.FileUtils
import com.kodyuzz.kanas.utils.network.FakeNetworkHelperImpl
import com.kodyuzz.kanas.utils.network.NetworkHelper
import com.kodyuzz.kanas.utils.rx.RxSchedulerProvider
import com.kodyuzz.kanas.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class ApplicationTestModule(private val application: InstagramApplication) {

    @Provides
    @Singleton
    fun provideApplication(): InstagramApplication = application

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
    fun provideDatabaseService(): DatabaseService =
        Room.databaseBuilder(
            application, DatabaseService::class.java,
            "kodyuzz-prefs"
        ).build()

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService {
        Networking.API_KEY = "FAKE_API_KEY"
        return FakeNetworkService()
    }


    @Singleton
    @Provides
    fun provideNetworkHelper(): NetworkHelper = FakeNetworkHelperImpl(application)
}