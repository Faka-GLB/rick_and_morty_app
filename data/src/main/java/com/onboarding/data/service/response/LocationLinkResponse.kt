package com.onboarding.data.service.response

import com.google.gson.annotations.SerializedName
import com.onboarding.data.util.ConstantUtil

data class LocationLinkResponse(
    @SerializedName("name")
    val locationName: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("url")
    val locationUrl: String = ConstantUtil.DEFAULT_STRING_VALUE,
)
