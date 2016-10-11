package com.guliash.countryquiz.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ProdAppModule.class)
public interface ProdAppComponent extends AppComponent {
}
