package com.guliash.quizzes.game

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.guliash.quizzes.answer.model.Answer
import com.guliash.quizzes.core.utils.collections.shuffle
import com.guliash.quizzes.core.utils.io.FileUtils
import com.guliash.quizzes.game.model.Country
import com.guliash.quizzes.game.model.Place
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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

    override fun generate(place: Place): List<Answer> {
        val answers = mutableListOf(Answer(place.country, true))
        val placeCountry = countries.find { it.name == place.country } ?: throw NullPointerException()
        val shuffled = countries.shuffle()
        answers.addAll(
                shuffled
                        .asSequence()
                        .filter {
                            it.name != placeCountry.name &&
                                    (it.region == placeCountry.region || it.subregion == placeCountry.subregion)
                        }
                        .take(3)
                        .map { it -> Answer(it.name, false) }
                        .toList()
        )
        if (answers.size < 4) {
            val need = 4 - answers.size
            answers.addAll(
                    shuffled
                            .asSequence()
                            .filter {
                                it.name != placeCountry.name && it.region != placeCountry.region &&
                                        it.subregion == placeCountry.subregion
                            }
                            .take(need)
                            .map { it -> Answer(it.name, false) }
                            .toList()
            )
        }
        answers.shuffle()
        return answers
    }
}