package com.guliash.countryquiz.provider;

import android.support.annotation.Nullable;

import com.guliash.countryquiz.quiz.models.Quiz;
import com.guliash.countryquiz.quiz.provider.Provider;

import java.util.List;

public class ProviderImp implements Provider {
    @Override
    public List<Quiz> getQuizzesByCriteria(@Nullable QuizCriteria criteria) {
        return null;
    }
}
