package com.guliash.countryquiz.quiz.question.presentation;

import android.graphics.Bitmap;

import com.guliash.countryquiz.RxTester;
import com.guliash.countryquiz.utils.image.ImageManager;
import com.guliash.countryquiz.quiz.question.QuestionContract;
import com.guliash.countryquiz.quiz.game.Game;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static com.guliash.countryquiz.data.StubQuizzes.QUIZ1;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuestionPresenterTest extends RxTester {


    private QuestionPresenter presenter;

    @Mock
    QuestionContract.View view;

    @Mock
    Game game;

    @Mock
    ImageManager imageManager;

    @Mock
    Bitmap bitmap;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        presenter = new QuestionPresenter(game, imageManager);

        when(game.next()).thenReturn(Observable.just(QUIZ1));
        when(imageManager.loadImage(anyString())).thenReturn(Observable.just(bitmap));
    }

    @Test
    public void attachView_normal_loadsGame() {
        presenter.attachView(view);

        verify(game).next();
        verify(view).showQuiz(QUIZ1, bitmap);
    }

    @Test
    public void answerSelected_normal_showsConfirmation() {
        presenter.attachView(view);

        presenter.onAnswerSelected(QUIZ1.getAnswer());

        verify(view).showConfirmation(QUIZ1.getAnswer());
    }

    @Test
    public void onAnswerConfirmed_answerCorrect_showsCorrectAnswer() {
        presenter.attachView(view);
        when(game.answer(anyString(), anyString())).thenReturn(Observable.just(true));

        presenter.onAnswerSelected(QUIZ1.getAnswer());

        presenter.onAnswerConfirmed();

        verify(view).showCorrectAnswer(QUIZ1.getId());
    }

    @Test
    public void onAnswerConfirmed_answerWrong_showsWrongAnswer() {
        presenter.attachView(view);
        when(game.answer(anyString(), anyString())).thenReturn(Observable.just(false));

        presenter.onAnswerSelected(QUIZ1.getAnswer());

        presenter.onAnswerConfirmed();

        verify(view).showWrongAnswer(QUIZ1.getAnswer());
    }
}
