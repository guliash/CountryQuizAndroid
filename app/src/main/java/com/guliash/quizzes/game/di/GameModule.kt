package com.guliash.quizzes.game.di

import com.guliash.quizzes.game.*
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

    @Binds
    @GameScope
    abstract fun answerGenerationStrategy(answerGenerationStrategy: AnswerGenerationStrategyImpl): AnswerGenerationStrategy
}