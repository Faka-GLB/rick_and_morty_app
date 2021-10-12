package com.onboarding.data.service

import com.onboarding.data.mapper.transform
import com.onboarding.data.service.api.RickAndMortyApi
import com.onboarding.data.service.response.CharacterBaseResponse
import com.onboarding.domain.entity.CharacterBase
import com.onboarding.domain.usecase.RickAndMortyRepository
import com.onboarding.domain.util.Result

class RickAndMortyRepositoryImpl : RickAndMortyRepository {
    private val generator = RickAndMortyRequestGenerator()

    override fun getAllCharacters(): Result<CharacterBase> {
        val callResponse = generator.createService(RickAndMortyApi::class.java)
            .callAllCharacters()
        return try {
            val response = callResponse.execute()
            Result.Success((response.body() as CharacterBaseResponse).transform())
        } catch (e: Exception) {
            Result.Failure(Exception(EXCEPTION_MESSAGE))
        }
    }

    companion object {
        private const val EXCEPTION_MESSAGE: String = "Bad request/response"
    }
}
