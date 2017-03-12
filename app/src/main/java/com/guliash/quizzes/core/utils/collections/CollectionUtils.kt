package com.guliash.quizzes.core.utils.collections

import java.util.*

fun <T> Collection<T>.joinToString(separator: String, beforeEach: String): String {
    val stringBuilder = StringBuilder()
    for ((index, element) in withIndex()) {
        stringBuilder.append(beforeEach).append(element)
        if (index != size - 1) {
            stringBuilder.append(separator)
        }
    }
    return stringBuilder.toString()
}

fun <T> List<T>.shuffle(): List<T> {
    val mutable = toMutableList()
    mutable.shuffle()
    return mutable.toList()
}

fun <T> MutableList<T>.shuffle() {
    val rnd = Random()
    for (i in 0..size - 2) {
        val swapWith = rnd.nextInt(size - i)
        swap(i, i + swapWith)
    }
}

fun <T> MutableList<T>.swap(x: Int, y: Int) {
    val value = this[x]
    this[x] = this[y]
    this[y] = value
}