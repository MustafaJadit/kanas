package com.kodyuzz.kanas.ui.main

import androidx.lifecycle.MutableLiveData
import com.kodyuzz.kanas.data.model.Post
import com.kodyuzz.kanas.ui.base.BaseViewModel
import com.kodyuzz.kanas.utils.common.Event
import com.kodyuzz.kanas.utils.network.NetworkHelper
import com.kodyuzz.kanas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class MainSharedViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    override fun onCreate() {}

    val homeRedirction = MutableLiveData<Event<Boolean>>()

    val newPost: MutableLiveData<Event<Post>> = MutableLiveData()

    fun onHomeRedirect() {
        homeRedirction.postValue(Event(true))
    }
}