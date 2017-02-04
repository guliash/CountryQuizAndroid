package com.guliash.quizzes.game.di

import com.guliash.quizzes.game.Game
import com.guliash.quizzes.game.GameImpl
import dagger.Module
import dagger.Provides

@Module
class GameModule {

    @Provides
    @GameScope
    fun game(): Game = GameImpl()

}