package com.guliash.countryquiz.provider;

import android.content.Context;
import android.location.Criteria;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guliash.countryquiz.R;
import com.guliash.countryquiz.quiz.models.Quiz;
import com.guliash.countryquiz.quiz.provider.Provider;
import com.guliash.countryquiz.utils.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FakeProvider implements Provider {

    private Context context;
    private int resourceId;
    private List<Quiz> quizzes;

    public FakeProvider(Context context, int resourceId) {
        this.context = context;
        this.resourceId = resourceId;
    }

    @Override
    public List<Quiz> getQuizzesByCriteria(@Nullable QuizCriteria criteria) {
        if(quizzes == null) {
            quizzes = readQuizzesFromStream(context.getResources().openRawResource(resourceId));
        }
        return filterQuizzes(quizzes, criteria);
    }

    private static List<Quiz> readQuizzesFromStream(InputStream inputStream) {
        return readQuizzesFromJson(IOUtils.readString(inputStream));

    }

    private static List<Quiz> readQuizzesFromJson(String json) {
        Type type = new TypeToken<List<Quiz>>(){}.getType();
        return new Gson().fromJson(json, type);
    }

    private static List<Quiz> filterQuizzes(List<Quiz> quizzes, QuizCriteria criteria) {
        List<Quiz> result = new ArrayList<>();
        if(criteria == null) {
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
}
