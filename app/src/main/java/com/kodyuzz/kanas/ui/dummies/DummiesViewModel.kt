package com.kodyuzz.kanas.ui.dummies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kodyuzz.kanas.data.model.Dummy
import com.kodyuzz.kanas.data.repository.DummyRepository
import com.kodyuzz.kanas.ui.base.BaseViewModel
import com.kodyuzz.kanas.utils.common.Resource
import com.kodyuzz.kanas.utils.common.Status
import com.kodyuzz.kanas.utils.network.NetworkHelper
import com.kodyuzz.kanas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable


class DummiesViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val dummyRepository: DummyRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private val dummyLiveData: MutableLiveData<Resource<List<Dummy>>> = MutableLiveData()

    fun getDummies(): LiveData<List<Dummy>> =
        Transformations.map(dummyLiveData) { it.data }

    fun isDummiesFetching(): LiveData<Boolean> =
        Transformations.map(dummyLiveData) { it.status == Status.LOADING }

    override fun onCreate() {
        if (dummyLiveData.value == null && checkInternetConnectionWithMessage()) {
            dummyLiveData.postValue(Resource.loading())
            compositeDisposable.add(
                dummyRepository.fetchDummy("MY_SAMPLE_DUMMY")
                    .subscribeOn(schedulerProvider.io())
                    .subscribe(
                        { dummyLiveData.postValue(Resource.success(it)) },
                        {
                            handleNetworkError(it)
                            dummyLiveData.postValue(Resource.error())
                        })
            )
        }
    }
}