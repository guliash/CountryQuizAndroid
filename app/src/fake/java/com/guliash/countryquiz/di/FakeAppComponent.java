package com.guliash.countryquiz.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = FakeAppModule.class)
public interface FakeAppComponent extends AppComponent {

}
