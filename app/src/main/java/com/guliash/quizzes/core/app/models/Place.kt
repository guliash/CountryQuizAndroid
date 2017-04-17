package com.guliash.quizzes.core.app.models

import com.google.gson.annotations.SerializedName
import com.guliash.quizzes.map.model.Position

data class Place(
        @SerializedName("_id") val id: String,
        @SerializedName("name") val name: String,
        @SerializedName("country") val country: String,
        @SerializedName("href") val href: String,
        @SerializedName("facts") val facts: List<String>,
        @SerializedName("position") val position: Position,
        @SerializedName("image") val image: Image
)