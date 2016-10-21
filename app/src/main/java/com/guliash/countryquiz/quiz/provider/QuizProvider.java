package com.guliash.countryquiz.quiz.provider;

import android.support.annotation.Nullable;

import com.guliash.countryquiz.quiz.model.Quiz;

import java.util.List;

public interface QuizProvider {

    class QuizCriteria {

        private String type;

        public QuizCriteria(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    List<Quiz> getQuizzesByCriteria(@Nullable QuizCriteria criteria);

    @Nullable Quiz getQuizById(String id);

}
