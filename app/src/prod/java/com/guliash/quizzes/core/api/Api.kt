package com.guliash.quizzes.core.api

import com.guliash.quizzes.game.model.Enigma
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {
    @GET("quizzes/countries")
    fun enigmas(): Observable<List<Enigma>>
}