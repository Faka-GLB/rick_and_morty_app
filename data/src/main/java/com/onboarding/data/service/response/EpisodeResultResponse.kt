package com.onboarding.data.service.response

import com.google.gson.annotations.SerializedName
import com.onboarding.data.util.ConstantUtil

data class EpisodeResultResponse(
    @SerializedName("id")
    val id: Int = ConstantUtil.DEFAULT_INT_VALUE,

    @SerializedName("name")
    val episodeName: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("air_date")
    val airDate: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("episode")
    val episodeCode: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("characters")
    val characters: List<String> = emptyList(),

    @SerializedName("url")
    val episodeUrl: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("created")
    val created: String = ConstantUtil.DEFAULT_STRING_VALUE
)
