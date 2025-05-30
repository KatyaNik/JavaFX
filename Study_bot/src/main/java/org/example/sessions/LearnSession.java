package org.example.sessions;

import org.example.model.Question;
import org.example.model.BankQuestion;

import java.util.List;

public class LearnSession implements StateSession {
    private Question currentQuestion;
    private final BankQuestion questionBank = new BankQuestion();

    @Override
    public Question action() {
        currentQuestion = questionBank.getRandomQuestion();
        return currentQuestion != null ? currentQuestion :
                new Question("Нет вопросов", List.of(), List.of());
    }

    @Override
    public boolean check(String answer) {
        return currentQuestion != null && currentQuestion.checkAnswer(answer);
    }

    @Override
    public String end() {
        return currentQuestion != null ?
                "Правильный ответ: " + currentQuestion.getCorrectAnswers() :
                "Не удалось определить правильный ответ";
    }

    @Override
    public State getState() {
        return State.ACTION; // Всегда в состоянии ACTION для этого режима
    }

    @Override
    public void setState(State state) {
        // Не меняем состояние в этом режиме
    }
}