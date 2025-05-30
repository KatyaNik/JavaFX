package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class BankQuestion {
    private List<Question> questions;

    public BankQuestion() {
        questions = new ArrayList<>();
        initializeQuestions();
    }

    private void initializeQuestions() {
        // Пример вопросов с вариантами ответов
        questions.add(new Question("Столица Франции",
                List.of("Париж"),
                List.of("Лондон", "Берлин","Россия","Москва")));

        questions.add(new Question("2 + 2",
                List.of("4", "четыре", "IV"),
                List.of("5", "3")));
        questions.add(new Question("Годы Великой Отечественной войны?",
                List.of("1941-1945"),
                List.of("1942-1945", "1941-1944")));
        questions.add(new Question("Какого языка программирования не существует?",
                List.of("Snape"),
                List.of("Python", "Pascal")));
        questions.add(new Question("Как зовут преподавателя по предмету Технология программирования?",
                List.of("Ольга Владимировна"),
                List.of("Оксана Валериевна", "Эдуард Ханифович")));
    }

    public Question getRandomQuestion() {
        if (questions.isEmpty()) return null;
        return questions.get((int)(Math.random() * questions.size()));
    }

    public List<Question> getQuestions(int count) {
        count = Math.min(count, questions.size());
        return new ArrayList<>(questions.subList(0, count));
    }
}
