package com.guliash.quizzes.game.questions.answer.di

interface ComponentProvider {

    fun create(module: AnswerModule): AnswerComponent

}