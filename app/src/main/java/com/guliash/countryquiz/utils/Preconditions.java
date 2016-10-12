package com.guliash.countryquiz.utils;

import android.support.annotation.Nullable;

public class Preconditions {

    public static <T> T checkNotNull(@Nullable T obj) {
        if(obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

}
