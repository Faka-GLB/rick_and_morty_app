package com.onboarding.data.database

import com.onboarding.data.database.dao.CharacterDao
import com.onboarding.data.database.dao.EpisodeDao
import com.onboarding.data.mapper.transformToCharacterResult
import com.onboarding.data.mapper.transformToEpisodeResult
import com.onboarding.data.mapper.transformToRoomCharacter
import com.onboarding.data.mapper.transformToRoomEpisode
import com.onboarding.domain.entity.CharacterResult
import com.onboarding.domain.entity.EpisodeResult
import com.onboarding.domain.usecase.RickAndMortyDatabase

class RickAndMortyDatabaseImpl(private val characterDao: CharacterDao, private val episodeDao: EpisodeDao) : RickAndMortyDatabase {

    override fun getCharacters() = characterDao.getAllCharacters().mapTo(mutableListOf(), { it.transformToCharacterResult() })

    override fun insertCharacters(characters: List<CharacterResult>) {
        characters.forEach {
            characterDao.insertCharacter(it.transformToRoomCharacter())
        }
    }

    override fun getEpisodes() = episodeDao.getAllEpisodes().mapTo(mutableListOf(), { it.transformToEpisodeResult() })

    override fun insertEpisodes(episodes: List<EpisodeResult>) {
        episodes.forEach {
            episodeDao.insertEpisode(it.transformToRoomEpisode())
        }
    }
}
