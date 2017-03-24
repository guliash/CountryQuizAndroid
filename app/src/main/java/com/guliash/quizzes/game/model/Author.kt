package com.guliash.quizzes.game.model

import com.google.gson.annotations.SerializedName

data class Author(
        @SerializedName("name") val name: String,
        @SerializedName("href") val href: String
)