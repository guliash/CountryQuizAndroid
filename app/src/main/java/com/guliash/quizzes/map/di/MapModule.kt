package com.guliash.quizzes.map.di

import com.guliash.quizzes.map.model.Position
import dagger.Module
import dagger.Provides

@Module
class MapModule(private val position: Position) {

    @Provides
    @MapScope
    fun providePosition() = position

}