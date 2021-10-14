package com.onboarding.data.mapper

import com.onboarding.data.database.entity.RoomCharacter
import com.onboarding.data.database.entity.RoomEpisode
import com.onboarding.data.database.entity.RoomLocationLink
import com.onboarding.domain.entity.CharacterResult
import com.onboarding.domain.entity.EpisodeResult
import com.onboarding.domain.entity.LocationLink

fun CharacterResult.transformToRoomCharacter() = RoomCharacter(
    this.id,
    this.name,
    this.status,
    this.species,
    this.type,
    this.gender,
    this.origin.transformToRoomLocationLink(),
    this.location.transformToRoomLocationLink(),
    this.image,
    this.episodes,
    this.characterUrl,
    this.created,
)

private fun LocationLink.transformToRoomLocationLink() = RoomLocationLink(
    this.locationName,
    this.locationUrl
)

fun RoomCharacter.transformToCharacterResult() = CharacterResult(
    this.id,
    this.name,
    this.status,
    this.species,
    this.type,
    this.gender,
    this.origin.transformToLocationLink(),
    this.location.transformToLocationLink(),
    this.image,
    this.episodes,
    this.characterUrl,
    this.created,
)

private fun RoomLocationLink.transformToLocationLink() = LocationLink(
    this.locationName,
    this.locationUrl
)

fun EpisodeResult.transformToRoomEpisode() = RoomEpisode(
    this.id,
    this.episodeName,
    this.airDate,
    this.episodeCode,
    this.characters,
    this.episodeUrl,
    this.created
)

fun RoomEpisode.transformToEpisodeResult() = EpisodeResult(
    this.id,
    this.name,
    this.airDate,
    this.episodeCode,
    this.characters,
    this.episodeUrl,
    this.created
)
