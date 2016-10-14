package com.guliash.countryquiz;

import android.app.Application;
import android.content.Context;

import com.guliash.countryquiz.di.AppComponent;
import com.guliash.countryquiz.di.AppFactory;

import timber.log.Timber;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        Timber.e("Application#onCreate");
        setAppComponent(AppFactory.createAppComponent(this));
    }

    public static App get(Context context) {
        return (App)context.getApplicationContext();
    }

    public void setAppComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
