package com.guliash.countryquiz.quiz.question.presentation;

import android.graphics.Bitmap;

import com.guliash.countryquiz.RxTester;
import com.guliash.countryquiz.data.StubQuizzes;
import com.guliash.countryquiz.utils.image.ImageManager;
import com.guliash.countryquiz.quiz.question.QuestionContract;
import com.guliash.countryquiz.quiz.game.Game;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class QuizPresenterTest extends RxTester {


    private QuizPresenter presenter;

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

        presenter = new QuizPresenter(game, imageManager);

        when(game.next()).thenReturn(Observable.just(StubQuizzes.QUIZ1));
        when(imageManager.loadImage(anyString())).thenReturn(Observable.just(bitmap));
    }

    @Test
    public void attachView_normal_loadsGame() {
        presenter.attachView(view);

        verify(game).next();
        verify(view).showQuiz(StubQuizzes.QUIZ1, bitmap);
    }
}
