package com.guliash.quizzes.question

import android.content.Context
import com.guliash.quizzes.R
import com.guliash.quizzes.core.url.Url
import com.guliash.quizzes.core.utils.ui.SpanFormatter
import com.guliash.quizzes.core.utils.url.UrlUtils
import com.guliash.quizzes.game.model.Attribution
import com.guliash.quizzes.question.di.QuestionScope
import javax.inject.Inject

@QuestionScope
class QuestionUtils @Inject constructor(private val context: Context,
                                        private val urlUtils: UrlUtils) {
    fun buildAttribution(attribution: Attribution): CharSequence {
        val str = context.getString(R.string.question_attribution)
        with(urlUtils) {
            return SpanFormatter.format(
                    str,
                    urlSpan(Url(attribution.source, context.getString(R.string.question_attributionImageSourceLink))),
                    urlSpan(Url(attribution.author.href, attribution.author.name)),
                    urlSpan(Url(attribution.license.href, attribution.license.name)))
        }
    }
}