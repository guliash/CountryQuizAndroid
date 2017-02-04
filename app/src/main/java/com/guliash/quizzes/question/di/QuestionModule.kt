package com.guliash.quizzes.question.di

import dagger.Module
import dagger.Provides

@Module
class QuestionModule(val whichQuestion: Int) {

    @Provides
    @QuestionScope
    fun provideWhichQuestion(): Int = whichQuestion

}