package com.guliash.quizzes.core.api

import com.guliash.quizzes.game.model.Place
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {
    @GET("quizzes/places")
    fun places(): Observable<List<Place>>
}