package com.guliash.countryquiz.quiz.question.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class QuestionAdapter extends FragmentStatePagerAdapter {

    public QuestionAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return QuestionFragment.newInstance();
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
}
