package com.onboarding.data.database

import com.onboarding.data.database.dao.CharacterDao
import com.onboarding.data.mapper.transform
import com.onboarding.data.mapper.transformToRoom
import com.onboarding.domain.entity.CharacterResult
import com.onboarding.domain.usecase.RickAndMortyDatabase

class RickAndMortyDatabaseImpl(private val characterDao: CharacterDao) : RickAndMortyDatabase {

    override fun getCharacters() = characterDao.getAllCharacters().mapTo(mutableListOf(), { it.transform() })

    override fun insertCharacters(characters: List<CharacterResult>) {
        characters.forEach {
            characterDao.insertCharacter(it.transformToRoom())
        }
    }
}
