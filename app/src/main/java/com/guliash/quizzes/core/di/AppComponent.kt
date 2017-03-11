package com.guliash.quizzes.core.di

import com.guliash.quizzes.core.di.rx.SchedulersModule
import com.guliash.quizzes.core.repository.di.RepositoryModule
import com.guliash.quizzes.game.di.GameComponent
import com.guliash.quizzes.map.di.MapComponent
import com.guliash.quizzes.map.di.MapModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, SchedulersModule::class, RepositoryModule::class))
interface AppComponent {
    fun plus(): GameComponent

    fun plus(mapModule: MapModule): MapComponent
}
