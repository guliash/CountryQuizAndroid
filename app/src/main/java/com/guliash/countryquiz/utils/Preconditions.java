package com.guliash.countryquiz.utils;

import android.support.annotation.Nullable;

import java.util.Locale;

public class Preconditions {

    public static <T> T checkNotNull(@Nullable T obj) {
        if(obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }

    public static void equals(int a, int b) {
        if(a != b) {
            throw new IllegalArgumentException(String.format(Locale.ENGLISH, "%d != %d", a, b));
        }
    }

    public static void notEquals(int a, int b) {
        if(a == b) {
            throw new IllegalArgumentException(String.format(Locale.ENGLISH, "%d == %d", a, b));
        }
    }

}
