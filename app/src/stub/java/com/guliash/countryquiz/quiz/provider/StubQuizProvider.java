package com.guliash.countryquiz.quiz.provider;

import android.content.Context;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guliash.countryquiz.quiz.model.Quiz;
import com.guliash.countryquiz.utils.IOUtils;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class StubQuizProvider implements QuizProvider {
    private Context context;
    private int resourceId;
    private List<Quiz> quizzes;

    public StubQuizProvider(Context context, int resourceId) {
        this.context = context;
        this.resourceId = resourceId;
    }

    @Override
    public List<Quiz> getQuizzesByCriteria(@Nullable QuizCriteria criteria) {
        loadQuizzesIfNeed();
        return filterQuizzes(quizzes, criteria);
    }

    @Nullable
    @Override
    public Quiz getQuizById(String id) {
        loadQuizzesIfNeed();
        return findQuiz(quizzes, id);
    }

    private void loadQuizzesIfNeed() {
        if (quizzes == null) {
            quizzes = readQuizzesFromStream(context.getResources().openRawResource(resourceId));
        }
    }

    private static List<Quiz> readQuizzesFromStream(InputStream inputStream) {
        return readQuizzesFromJson(IOUtils.readString(inputStream));

    }

    private static List<Quiz> readQuizzesFromJson(String json) {
        Type type = new TypeToken<List<Quiz>>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    private static List<Quiz> filterQuizzes(List<Quiz> quizzes, QuizCriteria criteria) {
        List<Quiz> result = new ArrayList<>();
        if (criteria == null) {
            result.addAll(quizzes);
        } else {
            for (Quiz quiz : quizzes) {
                if (quiz.getType().equals(criteria.getType())) {
                    result.add(quiz);
                }
            }
        }
        return result;
    }

    @Nullable
    private static Quiz findQuiz(List<Quiz> quizzes, String id) {
        for (Quiz quiz : quizzes) {
            if (quiz.getId().equals(id)) {
                return quiz;
            }
        }
        return null;
    }
}
