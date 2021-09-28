package com.onboarding.rick_and_morty_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onboarding.rick_and_morty_app.util.Event
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SplashViewModel : ViewModel() {
    private var mutableLiveDataSplash: MutableLiveData<Event<Data<Boolean>>> = MutableLiveData()

    fun getLiveDataSplash() = mutableLiveDataSplash

    fun loadMainActivity() = viewModelScope.launch {
        withContext(IO) {
            delay(LOAD_TIME)
            mutableLiveDataSplash.postValue(
                Event(
                    Data(
                        responseType = Status.LOAD_COMPLETE,
                        data = true
                    )
                )
            )
        }
    }

    companion object {
        private const val LOAD_TIME: Long = 4000
    }
}

data class Data<RequestData>(var responseType: Status, var data: RequestData? = null, var error: Exception? = null)

enum class Status { LOAD_COMPLETE }
