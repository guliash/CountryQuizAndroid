package com.guliash.countryquiz.quiz.question.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class QuizAdapter extends FragmentStatePagerAdapter {

    public QuizAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return QuizFragment.newInstance();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
}
