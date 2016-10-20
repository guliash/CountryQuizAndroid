package com.guliash.countryquiz.quiz.view;

import android.support.v4.view.ViewPager;
import android.view.View;

public class QuizPagerTransformer implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.8f;
    private static final float MIN_ALPHA = 0.5f;

    @Override
    public void transformPage(View page, float position) {
        if(position >= -1 && position <= 1) {
            float newScale = Math.max(MIN_SCALE, 1 - Math.abs(position));
            float newAlpha;
            if(position <= 0) {
                newAlpha = 1 + (1 - MIN_ALPHA) * position;
            } else {
                newAlpha = MIN_ALPHA + (1 - MIN_ALPHA) * (1 - position);
            }
            page.setScaleX(newScale);
            page.setScaleY(newScale);
            page.setAlpha(newAlpha);
        }
    }
}
