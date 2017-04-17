package com.guliash.quizzes.game.questions.answer.di

import com.guliash.quizzes.game.questions.answer.view.AnswerFragment
import dagger.Subcomponent

@AnswerScope
@Subcomponent(modules = arrayOf(AnswerModule::class))
interface AnswerComponent {

    fun inject(fragment: AnswerFragment)

}