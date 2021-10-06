package com.onboarding.domain.usecase

import com.onboarding.domain.entity.CharacterBase
import com.onboarding.domain.util.Result

interface RickAndMortyRepository {
    fun getAllCharacters(): Result<CharacterBase>
}
