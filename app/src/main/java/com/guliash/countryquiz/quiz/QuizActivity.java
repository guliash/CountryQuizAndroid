package com.guliash.countryquiz.quiz;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.guliash.countryquiz.R;
import com.guliash.countryquiz.core.ActivityModule;
import com.guliash.countryquiz.core.App;
import com.guliash.countryquiz.core.base.BaseActivity;
import com.guliash.countryquiz.quiz.answer.AnswerContract;
import com.guliash.countryquiz.quiz.provider.QuizProvider;
import com.guliash.countryquiz.quiz.question.view.QuestionAdapter;
import com.guliash.countryquiz.quiz.question.view.QuestionPagerTransformer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class QuizActivity extends BaseActivity {

    @BindView(R.id.quiz_pager)
    ViewPager viewPager;

    @Inject
    QuizProvider quizProvider;

    @Override
    protected void injectDependencies() {
        super.injectDependencies();
        mComponent = App.get(this).getAppComponent().plus(new ActivityModule(new Navigation()));
        mComponent.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Timber.e("PROVIDER %s", quizProvider);

        viewPager.setAdapter(new QuestionAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setPageTransformer(true, new QuestionPagerTransformer());

    }

    public class Navigation implements AnswerContract.Navigation {

        @Override
        public void answersNotSureSelected() {

        }

        @Override
        public void answersTryAnotherSelected() {

        }

        @Override
        public void answersNextSelected() {

        }
    }

}
