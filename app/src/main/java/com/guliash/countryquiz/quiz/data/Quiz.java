package com.guliash.countryquiz.quiz.data;

import java.util.List;

public class Quiz {

    private String id;
    private String type;
    private List<String> answers;
    private String answer;

    public Quiz(String id, String type, List<String> answers, String answer) {
        this.id = id;
        this.type = type;
        this.answers = answers;
        this.answer = answer;
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
}
