package com.guliash.quizzes.game

import com.guliash.quizzes.game.di.GameScope
import com.guliash.quizzes.question.model.*
import io.reactivex.Single
import javax.inject.Inject

@GameScope
class GameImpl @Inject constructor() : Game {

    private companion object Provider {
        val questions: List<Question> = arrayListOf(
                Question("Which country?", arrayListOf(Answer("Egypt", true), Answer("China", false),
                        Answer("Russia", false), Answer("Japan", false)),
                        Image("https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/Great_Sphinx_Closeup.JPG/640px-Great_Sphinx_Closeup.JPG",
                                Attribution(
                                        Url("https://commons.wikimedia.org/wiki/File:Great_Sphinx_Closeup.JPG", "Photo"),
                                        Url("https://en.wikipedia.org/wiki/User:Hamish2k", "Hamish2k"),
                                        Url("https://creativecommons.org/licenses/by-sa/3.0/deed.en", "CC BY-SA 3.0"))
                        )
                )
//                Question("Which country?", arrayListOf(Answer("Egypt", false), Answer("China", false),
//                        Answer("Afghanistan", true), Answer("Japan", false)),
//                        Image("https://upload.wikimedia.org/wikipedia/commons/5/53/Afghanistan_Statua_di_Budda_1.jpg", "")
//                )
        )
    }

    override fun question(which: Int): Single<Question> {
        return Single.just(questions[which % questions.size]);
    }

    override fun answer(question: Question, answer: Answer): Single<Verdict> {
        return if (answer.correct) Single.just(Verdict(answer, true)) else Single.just(Verdict(answer, false))
    }
}