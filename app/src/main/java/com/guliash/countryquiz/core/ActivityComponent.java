package com.guliash.countryquiz.core;

import com.guliash.countryquiz.quiz.QuizActivity;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(QuizActivity activity);

}
