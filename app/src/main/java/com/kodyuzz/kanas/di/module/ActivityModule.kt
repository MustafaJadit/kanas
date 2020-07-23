package com.kodyuzz.kanas.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodyuzz.kanas.data.repository.UserRepository
import com.kodyuzz.kanas.ui.base.BaseActivity
import com.kodyuzz.kanas.ui.login.LoginViewModel
import com.kodyuzz.kanas.ui.main.MainSharedViewModel
import com.kodyuzz.kanas.ui.main.MainViewModel
import com.kodyuzz.kanas.ui.splash.SplashViewModel
import com.kodyuzz.kanas.utils.ViewModelProviderFactory
import com.kodyuzz.kanas.utils.network.NetworkHelper
import com.kodyuzz.kanas.utils.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class ActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager = LinearLayoutManager(activity)

    @Provides
    fun provideMainViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): MainViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainViewModel::class) {
            MainViewModel(schedulerProvider, compositeDisposable, networkHelper)
        }
    ).get(MainViewModel::class.java)

    @Provides
    fun providesLoginViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): LoginViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(
            LoginViewModel::class
        ) {
            LoginViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                userRepository
            )
        }
    ).get(LoginViewModel::class.java)


    @Provides
    fun provideSplashViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        userRepository: UserRepository
    ): SplashViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(
            SplashViewModel::class
        ) {
            SplashViewModel(
                schedulerProvider,
                compositeDisposable,
                networkHelper,
                userRepository
            )
        }
    ).get(SplashViewModel::class.java)

    @Provides
    fun provideMainSharedViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper
    ): MainSharedViewModel = ViewModelProviders.of(
        activity, ViewModelProviderFactory(MainSharedViewModel::class) {
            MainSharedViewModel(schedulerProvider, compositeDisposable, networkHelper)
        }).get(MainSharedViewModel::class.java)
}