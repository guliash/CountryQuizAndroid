package com.guliash.countryquiz.core;

import com.guliash.countryquiz.quiz.game.GameModule;
import com.guliash.countryquiz.quiz.provider.QuizProviderModule;
import com.guliash.countryquiz.utils.image.ImageModule;
import com.guliash.countryquiz.utils.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, GameModule.class, ImageModule.class, NetworkModule.class,
        QuizProviderModule.class})
public interface AppComponent {

    ActivityComponent plus(ActivityModule activityModule);

}
