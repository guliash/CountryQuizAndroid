package com.guliash.quizzes.question

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import com.guliash.quizzes.R
import com.guliash.quizzes.core.ui.SpanFormatter
import com.guliash.quizzes.core.ui.UrlSpanFactory
import com.guliash.quizzes.question.di.QuestionScope
import com.guliash.quizzes.question.model.Attribution
import com.guliash.quizzes.core.url.Url
import com.guliash.quizzes.core.url.UrlUtils
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