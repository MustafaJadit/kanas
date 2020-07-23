package com.kodyuzz.kanas.ui.main

import androidx.lifecycle.MutableLiveData
import com.kodyuzz.kanas.ui.base.BaseViewModel
import com.kodyuzz.kanas.utils.common.Event
import com.kodyuzz.kanas.utils.network.NetworkHelper
import com.kodyuzz.kanas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(
    schedulerProvider, compositeDisposable, networkHelper
) {

    val profileNavigation=MutableLiveData<Event<Boolean>>()

    val homeNavigation= MutableLiveData<Event<Boolean>>()

    val photonavigation=MutableLiveData<Event<Boolean>>()

    override fun onCreate() {
        homeNavigation.postValue(Event(true))
     }

    fun onProfileSelected(){
        profileNavigation.postValue(Event(true))
    }

    fun onHomeSelected(){
        homeNavigation.postValue(Event(true))
    }

    fun onPhotoSelected(){
        photonavigation.postValue(Event(true))
    }



}