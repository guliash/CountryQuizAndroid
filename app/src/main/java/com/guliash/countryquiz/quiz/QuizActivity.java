package com.guliash.countryquiz.quiz;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.guliash.countryquiz.R;
import com.guliash.countryquiz.core.ActivityModule;
import com.guliash.countryquiz.core.App;
import com.guliash.countryquiz.core.base.BaseActivity;
import com.guliash.countryquiz.quiz.provider.QuizProvider;
import com.guliash.countryquiz.quiz.question.view.QuestionAdapter;
import com.guliash.countryquiz.quiz.question.view.QuestionFragment;
import com.guliash.countryquiz.quiz.question.view.QuestionPagerTransformer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuizActivity extends BaseActivity implements QuestionFragment.Provider {

    private static final int OFF_SCREEN_PAGE_LIMIT = 2;

    @BindView(R.id.quiz_pager)
    ViewPager viewPager;

    @Inject
    QuizProvider quizProvider;

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        mComponent = App.get(this).getAppComponent().plus(new ActivityModule());
        mComponent.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        viewPager.setAdapter(new QuestionAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(OFF_SCREEN_PAGE_LIMIT);
        viewPager.setPageTransformer(true, new QuestionPagerTransformer());
    }

    @Override
    public QuestionFragment.Callbacks provideQuestionCallbacks() {
        return mQuestionCallbacks;
    }

    private int getNextItemIndex() {
        return viewPager.getCurrentItem() + 1;
    }

    private QuestionFragment.Callbacks mQuestionCallbacks = new QuestionFragment.Callbacks() {
        @Override
        public void onNextSelected(String tag) {
            viewPager.setCurrentItem(getNextItemIndex(), true);
        }
    };
}
