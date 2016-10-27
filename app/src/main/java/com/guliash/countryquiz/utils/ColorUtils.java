package com.guliash.countryquiz.utils;

import android.graphics.Color;

public class ColorUtils {

    public static int getInverseColor(int color) {
        return Color.rgb(255 - Color.red(color),
                255 - Color.green(color),
                255 - Color.blue(color));
    }

}
