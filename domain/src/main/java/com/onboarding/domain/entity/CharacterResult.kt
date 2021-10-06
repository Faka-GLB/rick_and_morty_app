package com.onboarding.domain.entity

data class CharacterResult(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: LocationLink,
    val location: LocationLink,
    val image: String,
    val episodes: List<String>,
    val characterUrl: String,
    val created: String
)
