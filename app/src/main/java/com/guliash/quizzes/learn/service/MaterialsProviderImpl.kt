package com.guliash.quizzes.learn.service

import com.guliash.quizzes.core.app.models.Place
import com.guliash.quizzes.core.repository.Repository
import com.guliash.quizzes.core.utils.collections.shuffle
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class MaterialsProviderImpl @Inject constructor(private val repository: Repository) : MaterialsProvider {

    private val places: List<Place> by lazy {
        repository.places().shuffle()
    }

    override fun materials(): Observable<Place> {
        return Observable.defer { Observable.fromIterable(places) }
    }

    override fun material(which: Int): Single<Place> {
        return Single.defer { Single.just(places[which % places.size]) }
    }

    override fun material(id: String): Single<Place> {
        return Single.defer { Single.just(places.first { it.id == id }) }
    }
}