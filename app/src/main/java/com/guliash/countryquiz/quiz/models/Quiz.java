package com.guliash.countryquiz.quiz.models;

import java.util.List;

public class Quiz {

    private String id;
    private String type;
    private List<String> answers;
    private String answer;
    private String imageUrl;

    public Quiz(String id, String type, List<String> answers, String answer, String imageUrl) {
        this.id = id;
        this.type = type;
        this.answers = answers;
        this.answer = answer;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getAnswer() {
        return answer;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
