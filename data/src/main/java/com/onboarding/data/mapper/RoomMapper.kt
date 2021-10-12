package com.onboarding.data.mapper

import com.onboarding.data.database.entity.RoomCharacter
import com.onboarding.data.database.entity.RoomLocationLink
import com.onboarding.domain.entity.CharacterResult
import com.onboarding.domain.entity.LocationLink

fun CharacterResult.transformToRoom() = RoomCharacter(
    this.id,
    this.name,
    this.status,
    this.species,
    this.type,
    this.gender,
    this.origin.transformToRoom(),
    this.location.transformToRoom(),
    this.image,
    this.episodes,
    this.characterUrl,
    this.created,
)

private fun LocationLink.transformToRoom() = RoomLocationLink(
    this.locationName,
    this.locationUrl
)

fun RoomCharacter.transform() = CharacterResult(
    this.id,
    this.name,
    this.status,
    this.species,
    this.type,
    this.gender,
    this.origin.transform(),
    this.location.transform(),
    this.image,
    this.episodes,
    this.characterUrl,
    this.created,
)

private fun RoomLocationLink.transform() = LocationLink(
    this.locationName,
    this.locationUrl
)
