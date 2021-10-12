package com.onboarding.data.service.response

import com.google.gson.annotations.SerializedName
import com.onboarding.data.util.ConstantUtil

data class CharactersResultResponse(
    @SerializedName("id")
    val id: Int = ConstantUtil.DEFAULT_INT_VALUE,

    @SerializedName("name")
    val name: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("status")
    val status: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("species")
    val species: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("type")
    val type: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("gender")
    val gender: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("origin")
    val origin: LocationLinkResponse = LocationLinkResponse(),

    @SerializedName("location")
    val location: LocationLinkResponse = LocationLinkResponse(),

    @SerializedName("image")
    val image: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("episode")
    val episodes: List<String> = emptyList(),

    @SerializedName("url")
    val characterUrl: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("created")
    val created: String = ConstantUtil.DEFAULT_STRING_VALUE,
)
