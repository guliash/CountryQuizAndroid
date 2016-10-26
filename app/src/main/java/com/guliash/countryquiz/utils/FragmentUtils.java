package com.guliash.countryquiz.utils;

import android.support.v4.app.Fragment;

public class FragmentUtils {

    public static <T> T getListener(Fragment fragment, Class<T> clazz) {
        Preconditions.checkNotNull(fragment, clazz);
        if(clazz.isInstance(fragment.getTargetFragment())) {
            return clazz.cast(fragment.getTargetFragment());
        } else if(clazz.isInstance(fragment.getParentFragment())) {
            return clazz.cast(fragment.getParentFragment());
        } else if(clazz.isInstance(fragment.getContext())) {
            return clazz.cast(fragment.getContext());
        } else {
            throw new IllegalArgumentException("Should be instance of " + clazz.getName());
        }
    }

}
