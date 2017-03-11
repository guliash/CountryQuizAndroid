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

object CollectionUtils {

    fun <E> shuffle(list: List<E>): List<E> {
        val mutable = list.toMutableList()
        shuffle(mutable)
        return mutable
    }

    fun <E> shuffle(list: MutableList<E>) {
        val rnd = Random()
        for (i in 0..list.size - 2) {
            val swapWith = rnd.nextInt(list.size - i)
            swap(list, i, i + swapWith)
        }
    }


    fun <E> swap(list: MutableList<E>, x: Int, y: Int) {
        val value = list[x]
        list[x] = list[y]
        list[y] = value
    }

}