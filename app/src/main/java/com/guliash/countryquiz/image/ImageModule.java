package com.guliash.countryquiz.image;

import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageModule {

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
