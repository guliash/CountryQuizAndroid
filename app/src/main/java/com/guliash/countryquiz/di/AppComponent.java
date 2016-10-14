package com.guliash.countryquiz.di;

import com.guliash.countryquiz.MainActivity;
import com.guliash.countryquiz.quiz.QuizFragment;

public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(QuizFragment quizFragment);

}
