package com.guliash.quizzes.game.di

import com.guliash.quizzes.game.Game
import com.guliash.quizzes.game.GameImpl
import com.guliash.quizzes.game.Gamepad
import com.guliash.quizzes.game.GamepadImpl
import dagger.Binds
import dagger.Module

@Module
abstract class GameModule {
    @Binds
    @GameScope
    abstract fun game(game: GameImpl): Game

    @Binds
    @GameScope
    abstract fun gamepad(gamepad: GamepadImpl): Gamepad
}