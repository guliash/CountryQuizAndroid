package com.guliash.quizzes.core.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.util.Log
import android.view.View

class UrlSpan(val url: String) : ClickableSpan() {

    override fun updateDrawState(ds: TextPaint?) {
        super.updateDrawState(ds)
        ds!!.color = Color.rgb(0x44, 0x8A, 0xFF)
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