package com.onboarding.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.onboarding.domain.entity.EpisodeBase
import com.onboarding.domain.entity.EpisodeResult
import com.onboarding.domain.usecase.GetAllEpisodesUseCase
import com.onboarding.domain.usecase.GetAllEpisodesUseCaseImpl
import com.onboarding.domain.usecase.RickAndMortyDatabase
import com.onboarding.domain.usecase.RickAndMortyRepository
import com.onboarding.domain.util.Result
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetAllEpisodesUseCaseTest {
    private lateinit var episodeUseCase: GetAllEpisodesUseCase
    private val service: RickAndMortyRepository = mock()
    private val database: RickAndMortyDatabase = mock()
    private val episodeBase: EpisodeBase = mock()

    @Before
    fun init() {
        episodeUseCase = GetAllEpisodesUseCaseImpl(service, database)
    }

    @Test
    fun `get episodes - success`() {
        val successResult: Result.Success<EpisodeBase> = mock()
        val episodeMock: EpisodeResult = mock()
        val episodeList = listOf(episodeMock)
        whenever(successResult.data).thenReturn(episodeBase)
        whenever(episodeBase.results).thenReturn(episodeList)
        whenever(service.getAllEpisodes()).thenReturn(successResult)
        whenever(database.getEpisodes()).thenReturn(episodeList)
        val result = episodeUseCase.getAllEpisodes()
        verify(service).getAllEpisodes()
        verify(database).insertEpisodes(episodeList)
        verify(database).getEpisodes()
        assertEquals(episodeMock, (result as Result.Success).data.first())
    }

    @Test
    fun `get episodes - failure`() {
        val failureResult: Result.Failure = mock()
        val exception: Exception = Exception(ERROR_MESSAGE)
        whenever(failureResult.exception).thenReturn(exception)
        whenever(service.getAllEpisodes()).thenReturn(failureResult)
        whenever(database.getEpisodes()).thenReturn(emptyList())
        val result = episodeUseCase.getAllEpisodes()
        verify(service).getAllEpisodes()
        verify(database).getEpisodes()
        assertEquals(ERROR_MESSAGE, (result as Result.Failure).exception.message)
    }

    @Test
    fun `get episodes - from database`() {
        val exception: Exception = mock()
        val failureResult = Result.Failure(exception)
        val episodeMock: EpisodeResult = mock()
        val episodeList = listOf(episodeMock)
        whenever(service.getAllEpisodes()).thenReturn(failureResult)
        whenever(database.getEpisodes()).thenReturn(episodeList)
        val result = episodeUseCase.getAllEpisodes()
        verify(service).getAllEpisodes()
        verify(database).getEpisodes()
        assertEquals(episodeMock, (result as Result.Success).data.first())
    }

    companion object {
        private const val ERROR_MESSAGE = "Error"
    }
}
