package com.guliash.countryquiz.core;

import com.guliash.countryquiz.utils.image.ImageModule;
import com.guliash.countryquiz.utils.network.NetworkModule;
import com.guliash.countryquiz.quiz.game.GameModule;
import com.guliash.countryquiz.quiz.provider.QuizProviderModule;
import com.guliash.countryquiz.quiz.QuizActivity;
import com.guliash.countryquiz.quiz.question.view.QuizFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, GameModule.class, ImageModule.class, NetworkModule.class,
        QuizProviderModule.class})
public interface AppComponent {

    void inject(QuizActivity quizActivity);

    void inject(QuizFragment quizFragment);

    ActivityComponent plus(ActivityModule activityModule);

}
