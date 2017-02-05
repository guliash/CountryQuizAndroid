package com.guliash.quizzes.game

import com.guliash.quizzes.question.model.Answer
import com.guliash.quizzes.question.model.Question
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

class GameImpl : Game {

    private val answers: PublishSubject<Unit> = PublishSubject.create()

    override fun answers(): Observable<Unit> = answers

    private companion object Provider {
        val questions: List<Question> = arrayListOf(
                Question("Which country?", arrayListOf(Answer("Egypt", true), Answer("China", false),
                        Answer("Russia", false), Answer("Japan", false)),
                        "https://upload.wikimedia.org/wikipedia/commons/thumb/2/28/Great_Sphinx_Closeup.JPG/640px-Great_Sphinx_Closeup.JPG"),
                Question("Which country?", arrayListOf(Answer("Egypt", false), Answer("China", false),
                        Answer("Afghanistan", true), Answer("Japan", false)),
                        "https://upload.wikimedia.org/wikipedia/commons/5/53/Afghanistan_Statua_di_Budda_1.jpg"))
    }

    override fun question(which: Int): Single<Question> {
        return Single.just(questions[which % questions.size]);
    }

    override fun answer(question: Question) {
        answers.onNext(Unit)
    }
}