package com.onboarding.viewmodel

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.onboarding.domain.entity.CharacterBase
import com.onboarding.domain.entity.CharacterResult
import com.onboarding.domain.usecase.GetAllCharactersUseCase
import com.onboarding.domain.usecase.GetAllCharactersUseCaseImpl
import com.onboarding.domain.usecase.RickAndMortyDatabase
import com.onboarding.domain.usecase.RickAndMortyRepository
import com.onboarding.domain.util.Result
import com.onboarding.rick_and_morty_app.viewmodel.CharacterData
import com.onboarding.rick_and_morty_app.viewmodel.CharacterStatus
import com.onboarding.rick_and_morty_app.viewmodel.CharactersViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CharactersViewModelTest : ViewModelTest() {
    private lateinit var characterUseCase: GetAllCharactersUseCase
    private lateinit var viewModel: CharactersViewModel
    private val service: RickAndMortyRepository = mock()
    private val database: RickAndMortyDatabase = mock()
    private val characterBaseMock: CharacterBase = mock()

    @Before
    override fun init() {
        super.init()
        characterUseCase = GetAllCharactersUseCaseImpl(service, database)
        viewModel = CharactersViewModel(characterUseCase)
    }

    @Test
    fun `get characters - success`() {
        val livedata = viewModel.getLiveDataCharacters().testObserver()
        val successResult: Result.Success<CharacterBase> = mock()
        val charactersList: List<CharacterResult> = mock()
        val responseList = listOf<CharacterData<CharacterStatus>>(
            CharacterData(CharacterStatus.LOADING),
            CharacterData(CharacterStatus.SUCCESS)
        )
        whenever(successResult.data).thenReturn(characterBaseMock)
        whenever(characterBaseMock.results).thenReturn(charactersList)
        whenever(service.getAllCharacters()).thenReturn(successResult)
        whenever(database.getCharacters()).thenReturn(charactersList)
        runBlocking {
            viewModel.getCharacters().join()
        }
        verify(service).getAllCharacters()
        verify(database).insertCharacters(charactersList)
        assertEquals(responseList[FIRST_STATUS].responseType, livedata.observedValues[FIRST_STATUS]?.peekContent()?.responseType)
        assertEquals(responseList[SECOND_STATUS].responseType, livedata.observedValues[SECOND_STATUS]?.peekContent()?.responseType)
    }

    @Test
    fun `get characters - failure`() {
        val liveData = viewModel.getLiveDataCharacters().testObserver()
        val failureResult: Result.Failure = mock()
        val exception: Exception = mock()
        val responseList = listOf<CharacterData<CharacterStatus>>(
            CharacterData(CharacterStatus.LOADING),
            CharacterData(CharacterStatus.ERROR, error = exception)
        )
        whenever(failureResult.exception).thenReturn(exception)
        whenever(service.getAllCharacters()).thenReturn(failureResult)
        runBlocking {
            viewModel.getCharacters().join()
        }
        verify(service).getAllCharacters()
        assertEquals(responseList[FIRST_STATUS].responseType, liveData.observedValues[FIRST_STATUS]?.peekContent()?.responseType)
        assertEquals(responseList[SECOND_STATUS].responseType, liveData.observedValues[SECOND_STATUS]?.peekContent()?.responseType)
    }

    @Test
    fun `get characters - empty list response`() {
        val liveData = viewModel.getLiveDataCharacters().testObserver()
        val successResult: Result.Success<CharacterBase> = mock()
        val emptyList = emptyList<CharacterResult>()
        val responseList = listOf<CharacterData<CharacterStatus>>(
            CharacterData(CharacterStatus.LOADING),
            CharacterData(CharacterStatus.EMPTY_RESPONSE_LIST)
        )
        whenever(service.getAllCharacters()).thenReturn(successResult)
        whenever(successResult.data).thenReturn(characterBaseMock)
        whenever(characterBaseMock.results).thenReturn(emptyList)
        runBlocking {
            viewModel.getCharacters().join()
        }
        verify(service).getAllCharacters()
        assertEquals(responseList[FIRST_STATUS].responseType, liveData.observedValues[FIRST_STATUS]?.peekContent()?.responseType)
        assertEquals(responseList[SECOND_STATUS].responseType, liveData.observedValues[SECOND_STATUS]?.peekContent()?.responseType)
    }

    @Test
    fun `get characters - from database`() {
        val livedata = viewModel.getLiveDataCharacters().testObserver()
        val exception: Exception = mock()
        val failureResult = Result.Failure(exception)
        val characterMock: CharacterResult = mock()
        val charactersList = listOf(characterMock)
        val responseList = listOf<CharacterData<CharacterStatus>>(
            CharacterData(CharacterStatus.LOADING),
            CharacterData(CharacterStatus.SUCCESS)
        )
        whenever(service.getAllCharacters()).thenReturn(failureResult)
        whenever(database.getCharacters()).thenReturn(charactersList)
        runBlocking {
            viewModel.getCharacters().join()
        }
        verify(service).getAllCharacters()
        verify(database).getCharacters()
        assertEquals(responseList[FIRST_STATUS].responseType, livedata.observedValues[FIRST_STATUS]?.peekContent()?.responseType)
        assertEquals(responseList[SECOND_STATUS].responseType, livedata.observedValues[SECOND_STATUS]?.peekContent()?.responseType)
    }

    companion object {
        private const val FIRST_STATUS = 0
        private const val SECOND_STATUS = 1
    }
}
