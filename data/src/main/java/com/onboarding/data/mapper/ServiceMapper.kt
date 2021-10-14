package com.onboarding.data.mapper

import com.onboarding.data.service.response.CharacterBaseResponse
import com.onboarding.data.service.response.CharactersResultResponse
import com.onboarding.data.service.response.EpisodeBaseResponse
import com.onboarding.data.service.response.EpisodeResultResponse
import com.onboarding.data.service.response.InfoResponse
import com.onboarding.data.service.response.LocationLinkResponse
import com.onboarding.domain.entity.CharacterBase
import com.onboarding.domain.entity.CharacterResult
import com.onboarding.domain.entity.EpisodeBase
import com.onboarding.domain.entity.EpisodeResult
import com.onboarding.domain.entity.Info
import com.onboarding.domain.entity.LocationLink

fun CharacterBaseResponse.transformToCharacterBase() = CharacterBase(
    this.info.transformToInfo(),
    this.results.mapTo(mutableListOf(), { it.transformToCharacterResult() })
)

private fun InfoResponse.transformToInfo() = Info(
    this.count,
    this.pages,
    this.nextPage,
    this.previousPage
)

private fun CharactersResultResponse.transformToCharacterResult() = CharacterResult(
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
    this.created
)

private fun LocationLinkResponse.transformToLocationLink() = LocationLink(
    this.locationName,
    this.locationUrl
)

fun EpisodeBaseResponse.transformToEpisodeBase() = EpisodeBase(
    this.info.transformToInfo(),
    this.results.mapTo(mutableListOf(), { it.transformToEpisodeResult() })
)

private fun EpisodeResultResponse.transformToEpisodeResult() = EpisodeResult(
    this.id,
    this.episodeName,
    this.airDate,
    this.episodeCode,
    this.characters,
    this.episodeUrl,
    this.created
)
