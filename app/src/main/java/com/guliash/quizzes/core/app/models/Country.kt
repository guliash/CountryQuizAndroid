package com.guliash.quizzes.core.app.models

import com.google.gson.annotations.SerializedName

class Country(
        @SerializedName("name") val name: String,
        @SerializedName("region") val region: String,
        @SerializedName("subregion") val subregion: String
)