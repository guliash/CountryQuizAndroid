package com.guliash.countryquiz.provider;

import android.content.Context;
import android.support.annotation.Nullable;

import com.guliash.countryquiz.quiz.models.Quiz;
import com.guliash.countryquiz.quiz.provider.Provider;

import java.util.List;

public class FakeProvider implements Provider {

    private Context context;

    public FakeProvider(Context context) {
        this.context = context;
    }

    @Override
    public List<Quiz> getQuizzesByCriteria(@Nullable QuizCriteria criteria) {
        return null;
    }
}
