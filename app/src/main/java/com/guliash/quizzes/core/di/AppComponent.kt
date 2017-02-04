package com.guliash.quizzes.core.di

import com.guliash.quizzes.core.rx.SchedulersModule
import com.guliash.quizzes.game.di.GameComponent
import com.guliash.quizzes.game.di.GameModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, SchedulersModule::class))
interface AppComponent {
    fun plus(gameModule: GameModule): GameComponent
}
