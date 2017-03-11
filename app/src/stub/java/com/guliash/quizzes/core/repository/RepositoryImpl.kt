package com.guliash.quizzes.core.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.guliash.quizzes.core.utils.io.FileUtils
import com.guliash.quizzes.game.model.Enigma
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val fileUtils: FileUtils, private val context: Context) : Repository {
    override fun enigmas(): Observable<Enigma> = Observable.fromCallable({
        Gson().fromJson<List<Enigma>>(
                fileUtils.readWhole(context.assets.open("questions.json")),
                object : TypeToken<List<Enigma>>() {}.type
        )
    }).flatMap { list -> Observable.fromIterable(list) }
}