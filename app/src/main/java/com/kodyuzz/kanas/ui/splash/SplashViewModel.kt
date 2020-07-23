package com.kodyuzz.kanas.ui.splash

import androidx.lifecycle.MutableLiveData
import com.kodyuzz.kanas.data.repository.UserRepository
import com.kodyuzz.kanas.ui.base.BaseViewModel
import com.kodyuzz.kanas.utils.common.Event
import com.kodyuzz.kanas.utils.network.NetworkHelper
import com.kodyuzz.kanas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class SplashViewModel(
    schedulerProvider: SchedulerProvider,
    disposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    var userRepository: UserRepository
) :
    BaseViewModel(schedulerProvider, disposable, networkHelper) {

    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    val launchLogin: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    override fun onCreate() {
        if (userRepository.getCurrentUser() != null)
            launchMain.postValue(Event(emptyMap()))
        else
            launchLogin.postValue(Event(emptyMap()))

    }


}