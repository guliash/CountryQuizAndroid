package com.guliash.quizzes.game

import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.core.repository.Repository
import com.guliash.quizzes.core.utils.collections.CollectionUtils
import com.guliash.quizzes.game.di.GameScope
import com.guliash.quizzes.game.model.Enigma
import com.guliash.quizzes.question.model.Question
import com.guliash.quizzes.question.model.Verdict
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@GameScope
class GameImpl @Inject constructor(private val repository: Repository) : Game {

    private var enigmas: List<Enigma>? = null
    private var questions: List<Question>? = null

    override fun questions(): Observable<Question> {
        return enigmas().map { enigma -> Question(enigma, emptyList()) }
    }

    override fun question(which: Int): Single<Question> {
        return Single.fromCallable { questionsSync()[which % questions!!.size] }
    }

    override fun enigma(id: String): Single<Enigma> {
        return enigmas().filter { it -> it.id == id }.singleOrError()
    }

    override fun answer(question: Question, answer: Answer): Single<Verdict> {
        return if (answer.correct) Single.just(Verdict(answer, true)) else Single.just(Verdict(answer, false))
    }

    private fun enigmas(): Observable<Enigma> = Observable.defer { Observable.fromIterable(enigmasSync()) }

    private fun questionsSync(): List<Question> {
        synchronized(this) {
            enigmasSync()
            questions = enigmas!!.map { enigma -> Question(enigma, emptyList()) }
            return questions!!
        }
    }

    private fun enigmasSync(): List<Enigma> {
        synchronized(this) {
            if (enigmas === null) {
                enigmas = CollectionUtils.shuffle(
                        repository
                                .enigmas()
                                .toList()
                                .blockingGet()
                                .toList()
                )
            }
            return enigmas!!
        }
    }
}