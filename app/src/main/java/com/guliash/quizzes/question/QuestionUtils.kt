package com.guliash.quizzes.question

import android.content.Context
import com.guliash.quizzes.R
import com.guliash.quizzes.core.utils.ui.SpanFormatter
import com.guliash.quizzes.core.utils.url.UrlUtils
import com.guliash.quizzes.question.di.QuestionScope
import com.guliash.quizzes.question.model.Attribution
import javax.inject.Inject

@QuestionScope
class QuestionUtils @Inject constructor(private val context: Context,
                                        private val urlUtils: UrlUtils) {
    fun buildAttribution(attribution: Attribution): CharSequence {
        val str = context.getString(R.string.question_attribution)
        return SpanFormatter.format(str, urlUtils.url(attribution.resource),
                urlUtils.url(attribution.author), urlUtils.url(attribution.license))
    }
}