package com.guliash.quizzes.core.app.di.game

import com.guliash.quizzes.game.AnswerGenerationStrategy
import com.guliash.quizzes.game.AnswerGenerationStrategyImpl
import com.guliash.quizzes.game.Game
import com.guliash.quizzes.game.GameImpl
import dagger.Binds
import dagger.Module

@Module
abstract class GameDataModule {
    @Binds
    abstract fun game(game: GameImpl): Game

    @Binds
    abstract fun answerGenerationStrategy(answerGenerationStrategy: AnswerGenerationStrategyImpl): AnswerGenerationStrategy
}