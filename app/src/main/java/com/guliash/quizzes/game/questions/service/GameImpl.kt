package com.guliash.quizzes.game.questions.service

import com.guliash.quizzes.core.app.models.Place
import com.guliash.quizzes.core.repository.Repository
import com.guliash.quizzes.core.utils.collections.shuffle
import com.guliash.quizzes.game.questions.answer.model.Answer
import com.guliash.quizzes.game.questions.question.model.Question
import com.guliash.quizzes.game.questions.question.model.Verdict
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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