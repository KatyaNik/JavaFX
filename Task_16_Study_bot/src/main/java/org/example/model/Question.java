package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Question {
    private String text;
    private List<String> correctAnswers;
    private List<String> wrongAnswers;

    public Question(String text, List<String> correctAnswers, List<String> wrongAnswers) {
        this.text = text;
        this.correctAnswers = correctAnswers;
        this.wrongAnswers = wrongAnswers;
    }

    public Question() {
        this("", List.of(), List.of());
    }

    public String getQuestion() {
        return text;
    }

    public List<String> getAnswerOptions() {
        List<String> options = new ArrayList<>();
        // Берем 3 правильных ответа (или меньше, если их меньше)
        int correctToAdd = Math.min(3, correctAnswers.size());
        options.addAll(correctAnswers.subList(0, correctToAdd));

        // Добавляем 1 неправильный
        if (!wrongAnswers.isEmpty()) {
            options.add(wrongAnswers.get(0));
        }

        Collections.shuffle(options);
        return options;
    }

    public boolean checkAnswer(String answer) {
        return correctAnswers.contains(answer);
    }

    public String getCorrectAnswers() {
        return String.join(", ", correctAnswers);
    }
}