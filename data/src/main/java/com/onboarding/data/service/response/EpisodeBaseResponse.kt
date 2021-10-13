package com.onboarding.data.service.response

import com.google.gson.annotations.SerializedName

data class EpisodeBaseResponse(
    @SerializedName("info")
    val info: InfoResponse = InfoResponse(),

    @SerializedName("results")
    val results: List<EpisodeResultResponse> = emptyList()
)
