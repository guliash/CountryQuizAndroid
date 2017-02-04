package com.guliash.quizzes.question.di

import com.guliash.quizzes.question.view.QuestionFragment
import dagger.Subcomponent

@QuestionScope
@Subcomponent(modules = arrayOf(QuestionModule::class))
interface QuestionComponent {
    fun inject(fragment: QuestionFragment)
}