package com.onboarding.viewmodel

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.onboarding.domain.entity.EpisodeBase
import com.onboarding.domain.entity.EpisodeResult
import com.onboarding.domain.usecase.GetAllEpisodesUseCase
import com.onboarding.domain.usecase.GetAllEpisodesUseCaseImpl
import com.onboarding.domain.usecase.RickAndMortyDatabase
import com.onboarding.domain.usecase.RickAndMortyRepository
import com.onboarding.domain.util.Result
import com.onboarding.rick_and_morty_app.viewmodel.EpisodeData
import com.onboarding.rick_and_morty_app.viewmodel.EpisodeStatus
import com.onboarding.rick_and_morty_app.viewmodel.SeasonsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SeasonsViewModelTest : ViewModelTest() {
    private lateinit var episodesUseCase: GetAllEpisodesUseCase
    private lateinit var viewModel: SeasonsViewModel
    private val service: RickAndMortyRepository = mock()
    private val database: RickAndMortyDatabase = mock()
    private val episodeBaseMock: EpisodeBase = mock()

    @Before
    override fun init() {
        super.init()
        episodesUseCase = GetAllEpisodesUseCaseImpl(service, database)
        viewModel = SeasonsViewModel(episodesUseCase)
    }

    @Test
    fun `get episodes - success`() {
        val liveData = viewModel.getLiveDataEpisodes().testObserver()
        val successResult: Result.Success<EpisodeBase> = mock()
        val episodeMock: EpisodeResult = mock()
        val episodeList = listOf(episodeMock)
        val responseList = listOf<EpisodeData<EpisodeStatus>>(
            EpisodeData(EpisodeStatus.LOADING),
            EpisodeData(EpisodeStatus.SUCCESS)
        )
        whenever(successResult.data).thenReturn(episodeBaseMock)
        whenever(episodeBaseMock.results).thenReturn(episodeList)
        whenever(service.getAllEpisodes()).thenReturn(successResult)
        whenever(database.getEpisodes()).thenReturn(episodeList)
        runBlocking {
            viewModel.getEpisodes().join()
        }
        assertEquals(responseList[FIRST_STATUS].responseType, liveData.observedValues[FIRST_STATUS]?.peekContent()?.responseType)
        assertEquals(responseList[SECOND_STATUS].responseType, liveData.observedValues[SECOND_STATUS]?.peekContent()?.responseType)
    }

    @Test
    fun `get episodes - failure`() {
        val liveData = viewModel.getLiveDataEpisodes().testObserver()
        val failureResult: Result.Failure = mock()
        val exception: Exception = mock()
        val responseList = listOf<EpisodeData<EpisodeStatus>>(
            EpisodeData(EpisodeStatus.LOADING),
            EpisodeData(EpisodeStatus.ERROR, error = exception)
        )
        whenever(service.getAllEpisodes()).thenReturn(failureResult)
        whenever(failureResult.exception).thenReturn(exception)
        runBlocking {
            viewModel.getEpisodes().join()
        }
        assertEquals(responseList[FIRST_STATUS].responseType, liveData.observedValues[FIRST_STATUS]?.peekContent()?.responseType)
        assertEquals(responseList[SECOND_STATUS].responseType, liveData.observedValues[SECOND_STATUS]?.peekContent()?.responseType)
    }

    @Test
    fun `get episodes - empty list response`() {
        val liveData = viewModel.getLiveDataEpisodes().testObserver()
        val successResult: Result.Success<EpisodeBase> = mock()
        val responseList = listOf<EpisodeData<EpisodeStatus>>(
            EpisodeData(EpisodeStatus.LOADING),
            EpisodeData(EpisodeStatus.EMPTY_RESPONSE_LIST)
        )
        whenever(successResult.data).thenReturn(episodeBaseMock)
        whenever(episodeBaseMock.results).thenReturn(emptyList())
        whenever(service.getAllEpisodes()).thenReturn(successResult)
        whenever(database.getEpisodes()).thenReturn(emptyList())
        runBlocking {
            viewModel.getEpisodes().join()
        }
        assertEquals(responseList[FIRST_STATUS].responseType, liveData.observedValues[FIRST_STATUS]?.peekContent()?.responseType)
        assertEquals(responseList[SECOND_STATUS].responseType, liveData.observedValues[SECOND_STATUS]?.peekContent()?.responseType)
    }

    @Test
    fun `get episodes - from database`() {
        val liveData = viewModel.getLiveDataEpisodes().testObserver()
        val exception: Exception = mock()
        val failureResult = Result.Failure(exception)
        val episodeMock: EpisodeResult = mock()
        val episodeList = listOf(episodeMock)
        val responseList = listOf<EpisodeData<EpisodeStatus>>(
            EpisodeData(EpisodeStatus.LOADING),
            EpisodeData(EpisodeStatus.SUCCESS)
        )
        whenever(service.getAllEpisodes()).thenReturn(failureResult)
        whenever(database.getEpisodes()).thenReturn(episodeList)
        runBlocking {
            viewModel.getEpisodes().join()
        }
        assertEquals(responseList[FIRST_STATUS].responseType, liveData.observedValues[FIRST_STATUS]?.peekContent()?.responseType)
        assertEquals(responseList[SECOND_STATUS].responseType, liveData.observedValues[SECOND_STATUS]?.peekContent()?.responseType)
    }

    companion object {
        private const val FIRST_STATUS = 0
        private const val SECOND_STATUS = 1
    }
}
