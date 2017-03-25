package com.guliash.quizzes.game.di

import com.guliash.quizzes.game.*
import dagger.Binds
import dagger.Module

@Module
abstract class GameModule {
    @Binds
    abstract fun game(game: GameImpl): Game

    @Binds
    abstract fun gamepad(gamepad: GamepadImpl): Gamepad

    @Binds
    abstract fun answerGenerationStrategy(answerGenerationStrategy: AnswerGenerationStrategyImpl): AnswerGenerationStrategy
}