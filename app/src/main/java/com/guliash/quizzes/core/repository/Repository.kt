package com.guliash.quizzes.core.repository

import com.guliash.quizzes.game.model.Enigma
import io.reactivex.Observable

interface Repository {

    fun enigmas(): Observable<Enigma>

}