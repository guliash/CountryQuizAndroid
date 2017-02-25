package com.guliash.quizzes.game

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.guliash.quizzes.core.utils.io.FileUtils
import com.guliash.quizzes.question.model.Question
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val fileUtils: FileUtils, private val context: Context) : Repository {
    override fun questions(): Observable<Question> = Observable.fromCallable({
        Gson().fromJson<List<Question>>(
                fileUtils.readWhole(context.assets.open("questions.json")),
                object : TypeToken<List<Question>>() {}.type
        )
    }).flatMap { list -> Observable.fromIterable(list) }
}