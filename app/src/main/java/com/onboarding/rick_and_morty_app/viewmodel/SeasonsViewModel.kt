package com.onboarding.rick_and_morty_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onboarding.domain.entity.EpisodeResult
import com.onboarding.domain.usecase.GetAllEpisodesUseCase
import com.onboarding.domain.util.Result
import com.onboarding.rick_and_morty_app.util.Event
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SeasonsViewModel(private val getAllEpisodes: GetAllEpisodesUseCase) : ViewModel() {
    private var mutableLiveDataEpisodes: MutableLiveData<Event<EpisodeData<List<EpisodeResult>>>> = MutableLiveData()

    fun getLiveDataEpisodes() = mutableLiveDataEpisodes

    fun getEpisodes() = viewModelScope.launch {
        mutableLiveDataEpisodes.value = Event(EpisodeData(responseType = EpisodeStatus.LOADING))
        withContext(IO) { getAllEpisodes.getAllEpisodes() }.let {
            when (it) {
                is Result.Success<List<EpisodeResult>> -> {
                    if (it.data.isNotEmpty()) {
                        mutableLiveDataEpisodes.postValue(Event(EpisodeData(responseType = EpisodeStatus.SUCCESS, it.data)))
                    } else {
                        mutableLiveDataEpisodes.postValue(
                            Event(
                                EpisodeData(
                                    responseType = EpisodeStatus.EMPTY_RESPONSE_LIST,
                                    data = it.data
                                )
                            )
                        )
                    }
                }
                is Result.Failure -> {
                    mutableLiveDataEpisodes.postValue(Event(EpisodeData(responseType = EpisodeStatus.ERROR, error = it.exception)))
                }
            }
        }
    }
}

data class EpisodeData<RequestData>(var responseType: EpisodeStatus, var data: RequestData? = null, var error: Exception? = null)

enum class EpisodeStatus { SUCCESS, ERROR, LOADING, EMPTY_RESPONSE_LIST }
