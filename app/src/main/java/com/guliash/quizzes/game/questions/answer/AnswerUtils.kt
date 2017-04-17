package com.guliash.quizzes.game.questions.answer

import android.content.Context
import com.guliash.quizzes.R
import com.guliash.quizzes.core.app.models.Place
import com.guliash.quizzes.core.url.Url
import com.guliash.quizzes.core.utils.ui.SpanFormatter
import com.guliash.quizzes.core.utils.url.UrlUtils
import com.guliash.quizzes.game.questions.answer.di.AnswerScope
import javax.inject.Inject

@AnswerScope
class AnswerUtils @Inject constructor(private val context: Context,
                                      private val urlUtils: UrlUtils) {
    fun buildDescription(place: Place): CharSequence {
        val descriptionNotFormatted = context.getString(R.string.answer_description)

        return SpanFormatter.format(descriptionNotFormatted, urlUtils.urlSpan(Url(place.href, place.name)))
    }
}