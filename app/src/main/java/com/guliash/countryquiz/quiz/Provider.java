package com.guliash.countryquiz.quiz;

import android.support.annotation.Nullable;

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

    @Nullable Quiz getQuizById(String id);

}
