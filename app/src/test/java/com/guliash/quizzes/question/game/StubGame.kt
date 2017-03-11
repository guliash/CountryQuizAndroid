package com.guliash.quizzes.question.game

import com.guliash.quizzes.game.Game
import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.game.model.Enigma
import com.guliash.quizzes.question.model.Verdict
import io.reactivex.Single

class StubGame : Game {
    override fun answer(enigma: Enigma, answer: Answer): Single<Verdict> {
        return Single.just(Verdict(answer, true))
    }

    companion object {
        val FAKE_ENIGMA: Enigma = Enigma("test question",
                listOf(), "test url");
    }

    override fun question(which: Int): Single<Enigma> {
        return Single.just(FAKE_ENIGMA)
    }

}