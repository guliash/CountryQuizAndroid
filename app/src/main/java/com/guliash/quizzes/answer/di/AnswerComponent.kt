package com.guliash.quizzes.answer.di

import com.guliash.quizzes.answer.view.AnswerFragment
import dagger.Subcomponent

@AnswerScope
@Subcomponent(modules = arrayOf(AnswerModule::class))
interface AnswerComponent {

    fun inject(fragment: AnswerFragment)

}