package com.guliash.quizzes.core.repository

import com.guliash.quizzes.game.model.Place
import io.reactivex.Observable

interface Repository {

    fun places(): Observable<Place>

}