package com.onboarding.rick_and_morty_app.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onboarding.domain.entity.CharacterResult
import com.onboarding.domain.usecase.GetAllCharactersUseCase
import com.onboarding.domain.util.Result
import com.onboarding.rick_and_morty_app.util.Event
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(private val getAllCharacters: GetAllCharactersUseCase) : ViewModel() {
    private var mutableLiveDataCharacters: MutableLiveData<Event<CharacterData<List<CharacterResult>>>> = MutableLiveData()

    fun getLiveDataCharacters() = mutableLiveDataCharacters

    fun getCharacters() = viewModelScope.launch {
        mutableLiveDataCharacters.value = Event(CharacterData(responseType = CharacterStatus.LOADING))
        withContext(IO) { getAllCharacters.getAllCharacters() }.let {
            when (it) {
                is Result.Success<List<CharacterResult>> -> {
                    if (it.data.isNotEmpty()) {
                        mutableLiveDataCharacters.postValue(Event(CharacterData(responseType = CharacterStatus.SUCCESS, data = it.data)))
                    } else {
                        mutableLiveDataCharacters.postValue(
                            Event(
                                CharacterData(
                                    responseType = CharacterStatus.EMPTY_RESPONSE_LIST,
                                    data = it.data
                                )
                            )
                        )
                    }
                }
                is Result.Failure -> {
                    mutableLiveDataCharacters.postValue(Event(CharacterData(responseType = CharacterStatus.ERROR, error = it.exception)))
                }
            }
        }
    }
}

data class CharacterData<RequestData>(var responseType: CharacterStatus, var data: RequestData? = null, var error: Exception? = null)

enum class CharacterStatus { SUCCESS, ERROR, LOADING, EMPTY_RESPONSE_LIST }
