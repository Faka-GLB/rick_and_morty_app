package com.onboarding.domain.usecase

import com.onboarding.domain.entity.CharacterResult

interface RickAndMortyDatabase {
    fun getCharacters(): List<CharacterResult>
    fun insertCharacters(characters: List<CharacterResult>)
}
