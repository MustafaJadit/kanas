package com.kodyuzz.kanas.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.kodyuzz.kanas.data.repository.UserRepository
import com.kodyuzz.kanas.ui.base.BaseViewModel
import com.kodyuzz.kanas.utils.common.*
import com.kodyuzz.kanas.utils.network.NetworkHelper
import com.kodyuzz.kanas.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class LoginViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper,
    private val userRepository: UserRepository
) : BaseViewModel(schedulerProvider, compositeDisposable, networkHelper) {

    private val validationList: MutableLiveData<List<Validation>> = MutableLiveData()

    val launchMain: MutableLiveData<Event<Map<String, String>>> = MutableLiveData()

    val emailField: MutableLiveData<String> = MutableLiveData()

    val passwordField: MutableLiveData<String> = MutableLiveData()
    val loggingIn: MutableLiveData<Boolean> = MutableLiveData()

    val emailValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.EMAIL)
    val passwordValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.PASSWORD)

    private fun filterValidation(field: Validation.Field) =
        Transformations.map(validationList) {
            it.find { validation -> validation.field == field }
                ?.run { return@run this.resouce }
                ?: Resource.unknown()

        }

    override fun onCreate() {

    }

    fun onEmailChange(email: String) = emailField.postValue(email)

    fun onPasswordChange(email: String) = passwordField.postValue(email)

    fun onLogin() {
        val email = emailField.value
        val password = passwordField.value

        val validations = Validator.validateLoginField(email, password)
        validationList.postValue(validations)

        if (validations.isNotEmpty() && email != null && password != null) {
            val successValidation = validations.filter {
                it.resouce.status == Status.SUCCESS
            }
            if (successValidation.size == validations.size && checkInternetConnectionWithMessage()) {
                loggingIn.postValue(true)
                compositeDisposable.addAll(
                    userRepository.doUserLogin(email, password)
                        .subscribeOn(schedulerProvider.io())
                        .subscribe(
                            {
                                userRepository.saveCurrentUser(it)
                                loggingIn.postValue(false)
                                launchMain.postValue(Event(emptyMap()))
                            },
                            {
                                handleNetworkError(it)
                                loggingIn.postValue(false)
                            }
                        )
                )
            }
        }
    }

}