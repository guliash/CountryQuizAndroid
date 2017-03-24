package com.guliash.quizzes.game.model

import com.google.gson.annotations.SerializedName

data class Image(
        @SerializedName("href") val url: String,
        @SerializedName("attribution") val attribution: Attribution
)