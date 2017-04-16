package com.guliash.quizzes.core.app.di

import com.guliash.quizzes.core.app.di.game.GameDataModule
import com.guliash.quizzes.core.app.di.rx.SchedulersModule
import com.guliash.quizzes.core.repository.di.RepositoryModule
import com.guliash.quizzes.game.di.GameComponent
import com.guliash.quizzes.game.di.GameModule
import com.guliash.quizzes.map.di.MapComponent
import com.guliash.quizzes.map.di.MapModule
import com.guliash.quizzes.start.di.StartComponent
import com.guliash.quizzes.start.di.StartModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, SchedulersModule::class, RepositoryModule::class, GameDataModule::class))
interface AppComponent {
    fun plus(gameModule: GameModule): GameComponent

    fun plus(startModule: StartModule): StartComponent

    fun plus(mapModule: MapModule): MapComponent
}
