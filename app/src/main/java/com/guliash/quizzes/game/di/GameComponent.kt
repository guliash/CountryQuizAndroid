package com.guliash.quizzes.game.di

import com.guliash.quizzes.question.di.QuestionComponent
import com.guliash.quizzes.question.di.QuestionModule
import com.guliash.quizzes.question.view.QuestionsPagerFragment
import dagger.Subcomponent

@GameScope
@Subcomponent(modules = arrayOf(GameModule::class))
interface GameComponent {
    fun plus(questionModule: QuestionModule): QuestionComponent

    fun inject(questionsPagerFragment: QuestionsPagerFragment)
}