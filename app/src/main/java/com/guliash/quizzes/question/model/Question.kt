package com.guliash.quizzes.question.model

import com.guliash.quizzes.answer.model.Answer

data class Question(val question: String = "", val answers: List<Answer>, val image: Image)