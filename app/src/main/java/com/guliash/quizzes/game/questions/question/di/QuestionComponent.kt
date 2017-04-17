package com.guliash.quizzes.game.questions.question.di

import com.guliash.quizzes.game.questions.answer.di.AnswerComponent
import com.guliash.quizzes.game.questions.answer.di.AnswerModule
import com.guliash.quizzes.game.questions.question.view.QuestionFragment
import dagger.Subcomponent

@QuestionScope
@Subcomponent(modules = arrayOf(QuestionModule::class))
interface QuestionComponent {
    fun plus(module: AnswerModule): AnswerComponent

    fun inject(fragment: QuestionFragment)
}