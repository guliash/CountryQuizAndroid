package com.guliash.quizzes.core.repository

import com.guliash.quizzes.core.app.models.Place

interface Repository {

    fun places(): List<Place>

}