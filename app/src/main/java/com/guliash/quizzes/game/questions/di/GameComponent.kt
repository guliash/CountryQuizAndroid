package com.guliash.quizzes.game.questions.di

import com.guliash.quizzes.game.di.GameScope
import com.guliash.quizzes.game.questions.QuestionsGameActivity
import com.guliash.quizzes.game.questions.question.di.QuestionComponent
import com.guliash.quizzes.game.questions.question.di.QuestionModule
import com.guliash.quizzes.game.questions.question.view.QuestionsPagerFragment
import dagger.Subcomponent

@GameScope
@Subcomponent(modules = arrayOf(GameModule::class))
interface GameComponent {
    fun plus(questionModule: QuestionModule): QuestionComponent

    fun inject(questionsPagerFragment: QuestionsPagerFragment)

    fun inject(activity: QuestionsGameActivity)
}