package com.guliash.quizzes.start

import io.reactivex.Observable

interface StartView {

    fun learns(): Observable<Any>

    fun plays(): Observable<Any>

}