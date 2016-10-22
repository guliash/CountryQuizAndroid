package com.guliash.countryquiz.utils.image;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.ImageLoader;

import rx.Observable;

public class AUILImageManager implements ImageManager {

    ImageLoader imageLoader;

    public AUILImageManager(ImageLoader imageLoader) {
        this.imageLoader = imageLoader;
    }

    @Override
    public Observable<Bitmap> loadImage(String url) {
        return Observable.create(subscriber -> {
            Bitmap bitmap = imageLoader.loadImageSync(url);
            if(bitmap != null) {
                subscriber.onNext(bitmap);
            } else {
                subscriber.onError(new ImageLoadingException());
            }
            subscriber.onCompleted();
        });
    }
}
