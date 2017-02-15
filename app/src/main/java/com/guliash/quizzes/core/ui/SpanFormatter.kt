package com.guliash.quizzes.core.ui

import android.text.Spannable
import android.text.SpannableStringBuilder

object SpanFormatter {

    fun format(s: String, vararg spannables: Spannable): Spannable {
        val ssb = SpannableStringBuilder()
        val n = s.length
        var i = 0
        var cur = 0
        while (i < n) {
            if (i < n - 1) {
                if (s[i] == '%' && s[i + 1] == 's') {
                    ssb.append(spannables[cur])
                    cur++
                    i += 2
                    continue
                }
            }
            ssb.append(s[i])
            i++
        }
        return ssb
    }
}
