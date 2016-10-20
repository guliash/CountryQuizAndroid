package com.guliash.countryquiz.quiz.provider;

import android.content.Context;

import com.guliash.countryquiz.R;
import com.guliash.countryquiz.quiz.provider.QuizProvider;
import com.guliash.countryquiz.quiz.provider.StubQuizProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class QuizProviderModule {

    @Singleton
    @Provides
    public QuizProvider provideQuizProvider(Context context) {
        return new StubQuizProvider(context, R.raw.test);
    }

}
