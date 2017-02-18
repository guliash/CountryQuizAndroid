package com.guliash.quizzes.answer.di

import com.guliash.quizzes.question.model.Verdict
import dagger.Module
import dagger.Provides

@Module
class AnswerModule(private val verdict: Verdict, private val questionId: String) {

    @Provides
    @AnswerScope
    fun verdict() = verdict

    @Provides
    @AnswerScope
    @QuestionId
    fun questionId() = questionId

}
