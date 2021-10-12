package com.onboarding.data.service.response

import com.google.gson.annotations.SerializedName

data class CharacterBaseResponse(
    @SerializedName("info")
    val info: InfoResponse = InfoResponse(),

    @SerializedName("results")
    val results: List<CharactersResultResponse> = emptyList()
)
