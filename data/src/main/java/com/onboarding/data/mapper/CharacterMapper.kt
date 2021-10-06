package com.onboarding.data.mapper

import com.onboarding.data.service.response.CharacterBaseResponse
import com.onboarding.data.service.response.CharactersResultResponse
import com.onboarding.data.service.response.InfoResponse
import com.onboarding.data.service.response.LocationLinkResponse
import com.onboarding.domain.entity.CharacterBase
import com.onboarding.domain.entity.CharacterResult
import com.onboarding.domain.entity.Info
import com.onboarding.domain.entity.LocationLink

fun CharacterBaseResponse.transform() = CharacterBase(
    this.info.transform(),
    this.results.mapTo(mutableListOf(), { it.transform() })
)

private fun InfoResponse.transform() = Info(
    this.count,
    this.pages,
    this.nextPage,
    this.previousPage
)

private fun CharactersResultResponse.transform() = CharacterResult(
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
    this.created
)

private fun LocationLinkResponse.transform() = LocationLink(
    this.locationName,
    this.locationUrl
)
