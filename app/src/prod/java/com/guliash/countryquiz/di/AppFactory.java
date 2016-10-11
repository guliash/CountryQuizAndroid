package com.guliash.countryquiz.di;

import com.guliash.countryquiz.App;

public class AppFactory {

    public static ProdAppComponent createAppComponent(App app) {
        return DaggerProdAppComponent.builder().prodAppModule(new ProdAppModule(app)).build();
    }
}
