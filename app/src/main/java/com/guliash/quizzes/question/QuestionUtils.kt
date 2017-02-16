package com.guliash.quizzes.question

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import com.guliash.quizzes.R
import com.guliash.quizzes.core.ui.SpanFormatter
import com.guliash.quizzes.core.ui.UrlSpan
import com.guliash.quizzes.core.ui.UrlSpanFactory
import com.guliash.quizzes.question.di.QuestionScope
import com.guliash.quizzes.question.model.Attribution
import com.guliash.quizzes.question.model.Url
import javax.inject.Inject

@QuestionScope
class QuestionUtils @Inject constructor(private val context: Context,
                                        private val urlSpanFactory: UrlSpanFactory) {
    fun buildAttribution(attribution: Attribution): CharSequence {
        val str = context.getString(R.string.attribution)
        return SpanFormatter.format(str, url(attribution.resource), url(attribution.author),
                url(attribution.license))
    }

    fun url(url: Url): Spannable {
        val spannableString = SpannableString(url.link)
        spannableString.setSpan(urlSpanFactory.create(url.href), 0, url.link.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannableString
    }
}