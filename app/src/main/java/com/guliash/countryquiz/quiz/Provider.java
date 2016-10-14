package com.guliash.countryquiz.quiz;

import android.support.annotation.Nullable;

import com.guliash.countryquiz.quiz.Quiz;

import java.util.List;

public interface Provider {

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

}
