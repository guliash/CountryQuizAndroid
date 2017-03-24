package com.guliash.quizzes.core.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.guliash.quizzes.core.utils.io.FileUtils
import com.guliash.quizzes.game.model.Place
import io.reactivex.Observable
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val fileUtils: FileUtils, private val context: Context) : Repository {
    override fun places(): Observable<Place> = Observable.fromCallable({
        Gson().fromJson<List<Place>>(
                fileUtils.readWhole(context.assets.open("places.json")),
                object : TypeToken<List<Place>>() {}.type
        )
    }).flatMap { list -> Observable.fromIterable(list) }
}