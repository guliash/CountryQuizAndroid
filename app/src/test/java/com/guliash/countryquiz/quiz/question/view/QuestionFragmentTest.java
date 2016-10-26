package com.guliash.countryquiz.quiz.question.view;

import android.graphics.Bitmap;
import android.widget.Button;

import com.guliash.countryquiz.BuildConfig;
import com.guliash.countryquiz.R;
import com.guliash.countryquiz.RxTester;
import com.guliash.countryquiz.quiz.QuizActivity;
import com.guliash.countryquiz.quiz.model.Quiz;
import com.guliash.countryquiz.quiz.question.presentation.QuestionPresenter;
import com.guliash.countryquiz.quiz.provider.StubQuizProvider;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import static org.robolectric.shadows.support.v4.SupportFragmentTestUtil.startFragment;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class QuestionFragmentTest extends RxTester {

    private QuestionFragment questionFragment;
    private StubQuizProvider quizProvider;
    private Bitmap testImage;
    private List<Button> answerViews;

    @Before
    public void setup() {
        initFields();
        startFragment(questionFragment, QuizActivity.class);
        initViews();
        mockViewDependencies();
    }

    private void initFields() {
        questionFragment = new QuestionFragment();
        quizProvider = new StubQuizProvider(RuntimeEnvironment.application, R.raw.test);
        testImage = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
    }

    private void initViews() {
        answerViews = new ArrayList<>();
        answerViews.add((Button) questionFragment.getView().findViewById(R.id.answer1));
        answerViews.add((Button) questionFragment.getView().findViewById(R.id.answer2));
        answerViews.add((Button) questionFragment.getView().findViewById(R.id.answer3));
        answerViews.add((Button) questionFragment.getView().findViewById(R.id.answer4));
    }

    private void mockViewDependencies() {
        questionFragment.mPresenter = Mockito.mock(QuestionPresenter.class);
    }

    @Test
    public void showQuiz_normal_answersShown() {
        Quiz quiz = quizProvider.getQuizById("1");
        questionFragment.showQuiz(quiz, testImage);
        assertThatAnswersCorrectlyShown(quiz);
    }

    @Test
    public void selectAnswer_normal_callsPresenterMethod() {
        Quiz quiz = quizProvider.getQuizById("1");
        questionFragment.showQuiz(quiz, testImage);
        questionFragment.selectAnswer(quiz.getAnswers().get(0));
        Mockito.verify(questionFragment.mPresenter).onAnswerSelected(quiz.getAnswers().get(0));
    }

    private void assertThatAnswersCorrectlyShown(Quiz quiz) {
        for(int i = 0, count = quiz.getAnswers().size(); i < count; i++) {
            Assert.assertEquals(quiz.getAnswers().get(i), answerViews.get(i).getText().toString());
        }
    }

}
