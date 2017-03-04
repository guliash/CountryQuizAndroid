package com.guliash.quizzes.answer.di

import com.guliash.quizzes.answer.presenter.ActionsDelegate
import com.guliash.quizzes.question.model.Verdict
import dagger.Module
import dagger.Provides

@Module
class AnswerModule(private val verdict: Verdict, private val questionId: String,
                   private val actionsDelegate: ActionsDelegate) {

    @Provides
    @AnswerScope
    fun verdict() = verdict

    @Provides
    @AnswerScope
    @QuestionId
    fun questionId() = questionId

    @Provides
    @AnswerScope
    fun actionsDelegate() = actionsDelegate

}
