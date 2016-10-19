package com.guliash.countryquiz.di;

import android.content.Context;

import com.guliash.countryquiz.App;
import com.guliash.countryquiz.R;
import com.guliash.countryquiz.image.AUILImageManager;
import com.guliash.countryquiz.image.ImageManager;
import com.guliash.countryquiz.quiz.FakeProvider;
import com.guliash.countryquiz.quiz.Game;
import com.guliash.countryquiz.quiz.GameImp;
import com.guliash.countryquiz.quiz.Provider;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FakeAppModule {

    private App app;

    public FakeAppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return app;
    }

    @Provides
    @Singleton
    public Provider provideQuizzesProvider(Context context) {
        return new FakeProvider(context, R.raw.test);
    }

    @Provides
    @Singleton
    public Game provideGame(Provider provider) {
        return new GameImp(provider);
    }

    @Provides
    @Singleton
    public ImageLoader provideImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).build();
        ImageLoader.getInstance().init(config);
        return ImageLoader.getInstance();
    }

    @Provides
    @Singleton
    public ImageManager provideImageManager(ImageLoader imageLoader) {
        return new AUILImageManager(imageLoader);
    }

}
