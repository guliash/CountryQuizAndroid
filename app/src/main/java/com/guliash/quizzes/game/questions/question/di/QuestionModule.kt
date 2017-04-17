package com.guliash.quizzes.game.questions.question.di

import dagger.Module
import dagger.Provides

@Module
class QuestionModule(val whichQuestion: Int) {

    @Provides
    @QuestionScope
    fun provideWhichQuestion(): Int = whichQuestion

}