package com.guliash.countryquiz.utils;

import java.util.Locale;

public class Preconditions {

    public static void checkNotNull(Object... objects) {
        for(Object object : objects) {
            if(object == null) {
                throw new NullPointerException();
            }
        }
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

    public static <T> T checkType(Object object, Class<T> clazz) {
        return clazz.cast(object);
    }

}
