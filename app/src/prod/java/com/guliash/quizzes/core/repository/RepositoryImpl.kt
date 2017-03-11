package com.guliash.quizzes.core.repository

import com.guliash.quizzes.core.api.Api
import com.guliash.quizzes.game.model.Enigma
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: Api) : Repository {
    override fun enigmas(): Observable<Enigma> = api.enigmas().flatMap { it -> Observable.fromIterable(it) }
}