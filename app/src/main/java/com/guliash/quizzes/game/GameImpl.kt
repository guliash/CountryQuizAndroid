package com.guliash.quizzes.game

import com.guliash.quizzes.answer.model.Answer
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

    private var questions: List<Question>? = null

    override fun questions(): Observable<Question> {
        return Observable.fromIterable(questionsSync())
    }

    override fun question(which: Int): Single<Question> {
        val questions = questionsSync()
        return Single.just(questions[which % questions.size])
    }

    override fun enigma(questionId: String): Single<Enigma> {
        return Observable.fromIterable(questionsSync())
                .filter { it -> it.id == questionId }
                .map(Question::enigma)
                .singleOrError()
    }

    override fun answer(question: Question, answer: Answer): Single<Verdict> {
        return if (answer.correct) Single.just(Verdict(answer, true)) else Single.just(Verdict(answer, false))
    }

    private fun questionsSync(): List<Question> {
        synchronized(this) {
            if (questions === null) {
                questions = CollectionUtils.shuffle(
                        repository.questions()
                                .toList()
                                .blockingGet()
                                .map { question -> question.copy(answers = CollectionUtils.shuffle(question.answers)) }
                )
            }
            return questions!!
        }
    }
}