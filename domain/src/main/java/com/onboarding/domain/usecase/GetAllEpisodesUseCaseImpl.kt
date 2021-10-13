package com.onboarding.domain.usecase

import com.onboarding.domain.entity.EpisodeResult
import com.onboarding.domain.util.Result

interface GetAllEpisodesUseCase {
    fun getAllEpisodes(): Result<List<EpisodeResult>>
}

class GetAllEpisodesUseCaseImpl(private val service: RickAndMortyRepository, private val database: RickAndMortyDatabase) :
    GetAllEpisodesUseCase {

    override fun getAllEpisodes(): Result<List<EpisodeResult>> =
        when (val baseResponse = service.getAllEpisodes()) {
            is Result.Success -> {
                val episodesList = baseResponse.data.results
                database.insertEpisodes(episodesList)
                Result.Success(database.getEpisodes())
            }
            is Result.Failure -> {
                val episodesFromDatabase = database.getEpisodes()
                if (episodesFromDatabase.isEmpty()) {
                    Result.Failure(baseResponse.exception)
                } else {
                    Result.Success(episodesFromDatabase)
                }
            }
        }
}
