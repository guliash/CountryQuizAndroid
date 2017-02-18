package com.guliash.quizzes.game

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.core.io.FileUtils
import com.guliash.quizzes.game.di.GameScope
import com.guliash.quizzes.game.model.Enigma
import com.guliash.quizzes.question.model.Question
import com.guliash.quizzes.question.model.Verdict
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

@GameScope
class GameImpl @Inject constructor(private val context: Context, private val fileUtils: FileUtils) : Game {

    private var cache: List<Question> = emptyList()

    override fun question(which: Int): Single<Question> {
        return questions().map { questions -> questions[which % questions.size] }.singleOrError()
    }

    override fun enigma(questionId: String): Single<Enigma> {
        return questions()
                .flatMap { list -> Observable.fromIterable(list) }
                .filter { it -> it.id == questionId }
                .map(Question::enigma)
                .singleOrError()
    }

    override fun answer(question: Question, answer: Answer): Single<Verdict> {
        return if (answer.correct) Single.just(Verdict(answer, true)) else Single.just(Verdict(answer, false))
    }

    private fun questions(): Observable<List<Question>> =
            if (cache.isEmpty()) {
                loadFromAssets().doOnNext { it -> cache = it }
            } else {
                Observable.just(cache);
            }

    private fun loadFromAssets(): Observable<List<Question>> =
            Observable.fromCallable {
                Gson().fromJson<List<Question>>(
                        fileUtils.readWhole(context.assets.open("questions.json")),
                        object : TypeToken<List<Question>>() {}.type
                )
            }
}