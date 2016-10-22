package com.guliash.countryquiz.core;

import com.guliash.countryquiz.quiz.QuizActivity;
import com.guliash.countryquiz.quiz.question.view.QuestionFragment;

import dagger.Subcomponent;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(QuizActivity activity);

    void inject(QuestionFragment fragment);

}
