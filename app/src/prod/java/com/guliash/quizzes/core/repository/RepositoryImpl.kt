package com.guliash.quizzes.core.repository

import com.guliash.quizzes.core.api.Api
import com.guliash.quizzes.core.app.models.Place
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: Api) : Repository {
    override fun places(): List<Place> = api.places().blockingFirst()
}