package com.guliash.quizzes.core.url

import com.google.gson.annotations.SerializedName

data class Url(
        @SerializedName("href") val href: String,
        @SerializedName("link") val link: String
)