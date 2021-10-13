package com.onboarding.domain.entity

data class EpisodeResult(
    val id: Int,

    val episodeName: String,

    val airDate: String,

    val episodeCode: String,

    val characters: List<String>,

    val episodeUrl: String,

    val created: String
)
