package com.onboarding.data.service

import com.onboarding.data.mapper.transformToCharacterBase
import com.onboarding.data.mapper.transformToEpisodeBase
import com.onboarding.data.service.api.RickAndMortyApi
import com.onboarding.data.service.response.CharacterBaseResponse
import com.onboarding.data.service.response.EpisodeBaseResponse
import com.onboarding.domain.entity.CharacterBase
import com.onboarding.domain.entity.EpisodeBase
import com.onboarding.domain.usecase.RickAndMortyRepository
import com.onboarding.domain.util.Result

class RickAndMortyRepositoryImpl : RickAndMortyRepository {
    private val callResponse = RickAndMortyRequestGenerator().createService(RickAndMortyApi::class.java)

    override fun getAllCharacters(): Result<CharacterBase> {
        return try {
            val response = callResponse.callAllCharacters().execute()
            if (response.isSuccessful) {
                Result.Success((response.body() as CharacterBaseResponse).transformToCharacterBase())
            } else {
                Result.Failure(Exception(EXCEPTION_MESSAGE))
            }
        } catch (e: Exception) {
            Result.Failure(Exception(EXCEPTION_MESSAGE))
        }
    }

    override fun getAllEpisodes(): Result<EpisodeBase> {
        return try {
            val response = callResponse.callAllEpisodes().execute()
            if (response.isSuccessful) {
                Result.Success((response.body() as EpisodeBaseResponse).transformToEpisodeBase())
            } else {
                Result.Failure(Exception(EXCEPTION_MESSAGE))
            }
        } catch (e: Exception) {
            Result.Failure(Exception(EXCEPTION_MESSAGE))
        }
    }

    companion object {
        private const val EXCEPTION_MESSAGE: String = "Bad request/response"
    }
}
