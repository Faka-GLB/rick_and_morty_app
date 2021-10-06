package com.onboarding.domain.usecase

import com.onboarding.domain.entity.CharacterResult
import com.onboarding.domain.util.Result

interface GetAllCharactersUseCase {
    fun getAllCharacters(): Result<List<CharacterResult>>
}

class GetAllCharactersUseCaseImpl(private val service: RickAndMortyRepository, private val database: RickAndMortyDatabase) :
    GetAllCharactersUseCase {

    override fun getAllCharacters(): Result<List<CharacterResult>> =
        when (val baseResponse = service.getAllCharacters()) {
            is Result.Success -> {
                val charactersList = baseResponse.data.results
                database.insertCharacters(charactersList)
                Result.Success(database.getCharacters())
            }
            is Result.Failure -> {
                val charactersFromDatabase = database.getCharacters()
                if (charactersFromDatabase.isEmpty()) {
                    Result.Failure(baseResponse.exception)
                } else {
                    Result.Success(charactersFromDatabase)
                }
            }
        }
}
