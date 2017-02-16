package com.guliash.quizzes.core.ui

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UrlSpanFactory @Inject constructor(private val context: Context) {
    fun create(url: String) = UrlSpan(url, context)
}