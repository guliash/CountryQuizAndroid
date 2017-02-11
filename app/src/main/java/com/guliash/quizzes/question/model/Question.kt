package com.guliash.quizzes.question.model

data class Question(val question: String = "", val answers: List<Answer>, val image: Image)