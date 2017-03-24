package com.guliash.quizzes.answer

import android.content.Context
import com.guliash.quizzes.R
import com.guliash.quizzes.answer.di.AnswerScope
import com.guliash.quizzes.core.url.Url
import com.guliash.quizzes.core.utils.ui.SpanFormatter
import com.guliash.quizzes.core.utils.url.UrlUtils
import com.guliash.quizzes.game.model.Place
import javax.inject.Inject

@AnswerScope
class AnswerUtils @Inject constructor(private val context: Context,
                                      private val urlUtils: UrlUtils) {
    fun buildDescription(place: Place): CharSequence {
        val descriptionNotFormatted = context.getString(R.string.answer_description)

        return SpanFormatter.format(descriptionNotFormatted, urlUtils.urlSpan(Url(place.href, place.name)))
    }
}