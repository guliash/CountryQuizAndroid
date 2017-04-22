package com.guliash.quizzes.learn.service

import com.guliash.quizzes.core.app.models.Place
import io.reactivex.Observable
import io.reactivex.Single

interface MaterialsProvider {

    fun materials(): Observable<Place>

    fun material(which: Int): Single<Place>

}