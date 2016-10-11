package com.guliash.countryquiz.di;

import android.content.Context;

import com.guliash.countryquiz.App;
import com.guliash.countryquiz.provider.ProviderImp;
import com.guliash.countryquiz.quiz.provider.Provider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ProdAppModule {

    private App app;

    public ProdAppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    public Provider provideQuizzesProvider() {
        return new ProviderImp();
    }

}
