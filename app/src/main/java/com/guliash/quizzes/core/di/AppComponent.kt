package com.guliash.quizzes.core.di

import com.guliash.quizzes.core.di.rx.SchedulersModule
import com.guliash.quizzes.game.di.GameComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, SchedulersModule::class))
interface AppComponent {
    fun plus(): GameComponent
}
