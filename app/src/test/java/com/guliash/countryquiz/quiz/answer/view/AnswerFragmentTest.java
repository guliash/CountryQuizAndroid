package com.guliash.countryquiz.quiz.answer.view;

import com.guliash.countryquiz.BuildConfig;
import com.guliash.countryquiz.R;
import com.guliash.countryquiz.RxTester;
import com.guliash.countryquiz.quiz.QuizActivity;
import com.guliash.countryquiz.quiz.answer.presentation.AnswerPresenter;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.guliash.countryquiz.data.StubQuizzes.QUIZ1;
import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class AnswerFragmentTest extends RxTester {

    @Mock
    AnswerPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void newInstance_ok_passedFieldsAreSet() {
        AnswerFragment fragment = AnswerFragment.newInstance(QUIZ1.getId(), QUIZ1.getAnswer());
        Assert.assertEquals(QUIZ1.getId(), fragment.getArguments().getString(AnswerFragment.QUIZ_ID_EXTRA));
        Assert.assertEquals(QUIZ1.getAnswer(), fragment.getArguments().getString(AnswerFragment.SELECTED_ANSWER_EXTRA));
    }

    @Test
    public void showConfirmation_ok_showsConfirmation() {
        AnswerFragment fragment = AnswerFragment.newInstance(QUIZ1.getId(), QUIZ1.getAnswer());
        startFragment(fragment, QuizActivity.class);

        fragment.showConfirmation(QUIZ1.getAnswer());

        Assert.assertEquals(fragment.getString(R.string.confirmation_answer_dialog_title),
                fragment.titleView.getText().toString());
        Assert.assertEquals(fragment.getString(R.string.confirmation_text, QUIZ1.getAnswer()),
                fragment.confirmationText.getText().toString());
    }

}
