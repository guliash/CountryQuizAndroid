package com.guliash.quizzes.core.utils.url

import android.text.Spannable
import android.text.SpannableString
import com.guliash.quizzes.core.url.Url
import com.guliash.quizzes.core.url.UrlSpanFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UrlUtils @Inject constructor(private val urlSpanFactory: UrlSpanFactory) {
    fun urlSpan(url: Url): Spannable {
        val spannableString = SpannableString(url.link)
        if (url.href.isNotEmpty()) {
            spannableString.setSpan(urlSpanFactory.create(url.href), 0, url.link.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return spannableString
    }
}