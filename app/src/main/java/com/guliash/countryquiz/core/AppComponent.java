package com.guliash.countryquiz.core;

import com.guliash.countryquiz.image.ImageModule;
import com.guliash.countryquiz.network.NetworkModule;
import com.guliash.countryquiz.quiz.game.GameModule;
import com.guliash.countryquiz.quiz.provider.QuizProviderModule;
import com.guliash.countryquiz.quiz.view.MainActivity;
import com.guliash.countryquiz.quiz.view.QuizFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, GameModule.class, ImageModule.class, NetworkModule.class,
        QuizProviderModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(QuizFragment quizFragment);

}
