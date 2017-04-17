package com.guliash.quizzes.core.app.di.game

import com.guliash.quizzes.game.questions.service.AnswerGenerationStrategy
import com.guliash.quizzes.game.questions.service.AnswerGenerationStrategyImpl
import com.guliash.quizzes.game.questions.service.Game
import com.guliash.quizzes.game.questions.service.GameImpl
import dagger.Binds
import dagger.Module

@Module
abstract class GameDataModule {
    @Binds
    abstract fun game(game: GameImpl): Game

    @Binds
    abstract fun answerGenerationStrategy(answerGenerationStrategy: AnswerGenerationStrategyImpl): AnswerGenerationStrategy
}