package com.guliash.quizzes.core.repository

import com.guliash.quizzes.core.app.models.Place
import io.reactivex.Observable

interface Repository {

    fun places(): Observable<Place>

}