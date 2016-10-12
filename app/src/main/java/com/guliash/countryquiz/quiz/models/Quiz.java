package com.guliash.countryquiz.quiz.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Quiz {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("link")
    private String link;

    @SerializedName("type")
    private String type;

    @SerializedName("answers")
    private List<String> answers;

    @SerializedName("answer")
    private String answer;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("attribution")
    private String attribution;

    public Quiz(String id, String name, String link, String type, List<String> answers,
                String answer, String imageUrl, String attribution) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.type = type;
        this.answers = answers;
        this.answer = answer;
        this.imageUrl = imageUrl;
        this.attribution = attribution;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
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

    public String getAttribution() {
        return attribution;
    }

    @Override
    public String toString() {
        return String.format("{ id: %s, name: %s }", id, name);
    }
}
