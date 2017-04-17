package com.guliash.quizzes.core.app.models

import com.google.gson.annotations.SerializedName

data class Attribution(
        @SerializedName("source") val source: String,
        @SerializedName("author") val author: Author,
        @SerializedName("license") val license: License
)