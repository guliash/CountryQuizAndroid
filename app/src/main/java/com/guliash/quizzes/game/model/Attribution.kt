package com.guliash.quizzes.game.model

import com.google.gson.annotations.SerializedName
import com.guliash.quizzes.core.url.Url

data class Attribution(
        @SerializedName("resource") val resource: Url,
        @SerializedName("author") val author: Url,
        @SerializedName("license") val license: Url
)