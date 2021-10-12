package com.onboarding.data.service.response

import com.google.gson.annotations.SerializedName
import com.onboarding.data.util.ConstantUtil

data class InfoResponse(
    @SerializedName("count")
    val count: Int = ConstantUtil.DEFAULT_INT_VALUE,

    @SerializedName("pages")
    val pages: Int = ConstantUtil.DEFAULT_INT_VALUE,

    @SerializedName("next")
    val nextPage: String = ConstantUtil.DEFAULT_STRING_VALUE,

    @SerializedName("prev")
    val previousPage: String = ConstantUtil.DEFAULT_STRING_VALUE
)
