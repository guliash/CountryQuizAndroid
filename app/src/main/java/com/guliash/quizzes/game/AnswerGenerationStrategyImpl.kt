package com.guliash.quizzes.game

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.core.utils.collections.shuffle
import com.guliash.quizzes.core.utils.io.FileUtils
import com.guliash.quizzes.game.di.GameScope
import com.guliash.quizzes.game.model.Country
import com.guliash.quizzes.game.model.Enigma
import javax.inject.Inject

@GameScope
class AnswerGenerationStrategyImpl @Inject constructor(
        private val context: Context,
        private val fileUtils: FileUtils
) : AnswerGenerationStrategy {

    private val countries: List<Country> by lazy { loadCountries() }

    private fun loadCountries(): List<Country> =
            Gson().fromJson<List<Country>>(
                    fileUtils.readWhole(context.assets.open("countries.json")),
                    object : TypeToken<List<Country>>() {}.type
            )

    override fun generate(enigma: Enigma): List<Answer> {
        val answers = mutableListOf(Answer(enigma.country, true))
        answers.addAll(
                countries.shuffle()
                        .asSequence()
                        .filter { it.name != enigma.country }
                        .take(3)
                        .map { it -> Answer(it.name, false) }
                        .toList()
        )
        answers.shuffle()
        return answers
    }
}