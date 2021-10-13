package com.onboarding.domain.usecase

import com.onboarding.domain.entity.CharacterResult
import com.onboarding.domain.entity.EpisodeResult

interface RickAndMortyDatabase {
    fun getCharacters(): List<CharacterResult>
    fun insertCharacters(characters: List<CharacterResult>)
    fun getEpisodes(): List<EpisodeResult>
    fun insertEpisodes(episodes: List<EpisodeResult>)
}
