package com.guliash.quizzes.question.game

import com.guliash.quizzes.game.Game
import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.game.model.Place
import com.guliash.quizzes.question.model.Verdict
import io.reactivex.Single

class StubGame : Game {
    override fun answer(place: Place, answer: Answer): Single<Verdict> {
        return Single.just(Verdict(answer, true))
    }

    companion object {
        val FAKE_PLACE: Place = Place("test question",
                listOf(), "test urlSpan");
    }

    override fun question(which: Int): Single<Place> {
        return Single.just(FAKE_PLACE)
    }

}