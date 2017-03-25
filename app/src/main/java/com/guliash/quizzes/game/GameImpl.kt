package com.guliash.quizzes.game

import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.core.repository.Repository
import com.guliash.quizzes.core.utils.collections.shuffle
import com.guliash.quizzes.game.di.GameScope
import com.guliash.quizzes.game.model.Place
import com.guliash.quizzes.question.model.Question
import com.guliash.quizzes.question.model.Verdict
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@GameScope
class GameImpl @Inject constructor(
        private val repository: Repository,
        private val answerGenerationStrategy: AnswerGenerationStrategy
) : Game {

    private val places: List<Place> by lazy {
        repository
                .places()
                .toList()
                .blockingGet()
                .toList()
                .shuffle()
    }

    private val questions: List<Question> by lazy {
        places.map { place -> Question(place, answerGenerationStrategy.generate(place)) }
    }

    override fun questions(): Observable<Question> {
        return places().map { place -> Question(place, emptyList()) }
    }

    override fun question(which: Int): Single<Question> {
        return Single.fromCallable { questions[which % questions.size] }
    }

    override fun place(id: String): Single<Place> {
        return places().filter { it -> it.id == id }.singleOrError()
    }

    override fun answer(question: Question, answer: Answer): Single<Verdict> {
        return if (answer.correct) Single.just(Verdict(answer, true)) else Single.just(Verdict(answer, false))
    }

    private fun places(): Observable<Place> = Observable.defer { Observable.fromIterable(places) }
}