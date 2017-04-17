package com.guliash.quizzes.core.app.models

import com.google.gson.annotations.SerializedName

data class License(
        @SerializedName("name") val name: String,
        @SerializedName("href") val href: String
)