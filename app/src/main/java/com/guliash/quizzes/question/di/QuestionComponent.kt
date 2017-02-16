package com.guliash.quizzes.question.di

import com.guliash.quizzes.answer.di.AnswerComponent
import com.guliash.quizzes.answer.di.AnswerModule
import com.guliash.quizzes.question.view.QuestionFragment
import dagger.Subcomponent

@QuestionScope
@Subcomponent(modules = arrayOf(QuestionModule::class))
interface QuestionComponent {
    fun plus(module: AnswerModule): AnswerComponent

    fun inject(fragment: QuestionFragment)
}