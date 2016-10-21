package com.guliash.countryquiz.image;

import android.graphics.Bitmap;

import rx.Observable;

public interface ImageManager {

    class ImageLoadingException extends RuntimeException {

    }

    Observable<Bitmap> loadImage(String url);

}
