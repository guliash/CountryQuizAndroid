package com.guliash.quizzes.core.utils

import java.util.*

object CollectionUtils {

    fun <E> shuffle(list: List<E>): List<E> {
        val mutable = list.toMutableList()
        shuffle(mutable)
        return mutable
    }

    fun <E> shuffle(list: MutableList<E>) {
        val rnd = Random()
        println(list)
        for (i in 0..list.size - 2) {
            val swapWith = rnd.nextInt(list.size - i)
            swap(list, i, i + swapWith)
        }
        println(list)
    }


    fun <E> swap(list: MutableList<E>, x: Int, y: Int) {
        val value = list[x]
        list[x] = list[y]
        list[y] = value
    }

}