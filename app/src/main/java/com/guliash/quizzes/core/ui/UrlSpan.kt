package com.guliash.quizzes.core.ui

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.content.ContextCompat
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View
import com.guliash.quizzes.R

class UrlSpan(val url: String, private val context: Context) : ClickableSpan() {

    override fun updateDrawState(ds: TextPaint?) {
        super.updateDrawState(ds)
        ds!!.color = ContextCompat.getColor(context, R.color.question_answerLinkColor)
    }

    override fun onClick(view: View?) {
        val uri = Uri.parse(url)
        val context = view!!.context
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Log.w("UrlSpan", "Activity was not found")
        }

    }
}