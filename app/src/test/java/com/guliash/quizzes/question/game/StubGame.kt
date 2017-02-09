package com.guliash.quizzes.question.game

import com.guliash.quizzes.game.Game
import com.guliash.quizzes.question.model.Answer
import com.guliash.quizzes.question.model.Question
import com.guliash.quizzes.question.model.Verdict
import io.reactivex.Single

class StubGame : Game {
    override fun answer(question: Question, answer: Answer): Single<Verdict> {
        return Single.just(Verdict(answer, true))
    }

    companion object {
        val fakeQuestion: Question = Question("test question",
                listOf(), "test url");
    }

    override fun question(which: Int): Single<Question> {
        return Single.just(fakeQuestion)
    }

}