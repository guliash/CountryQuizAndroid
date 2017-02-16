package com.guliash.quizzes.answer.di

interface ComponentProvider {

    fun create(module: AnswerModule): AnswerComponent

}