package com.guliash.quizzes.learn.preview.view

import com.guliash.quizzes.core.app.models.Place
import io.reactivex.Observable

interface PreviewView {

    fun showMaterial(place: Place)

    fun selections(): Observable<Any>

}