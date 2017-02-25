package com.guliash.quizzes.core.di.rx

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class SchedulersModule {

    @Provides
    @Main
    fun main(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @IO
    fun io(): Scheduler = Schedulers.io()

    @Provides
    @Computation
    fun computation(): Scheduler = Schedulers.computation()
}