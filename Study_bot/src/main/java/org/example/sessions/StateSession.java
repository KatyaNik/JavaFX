package org.example.sessions;

import org.example.model.Question;

public interface StateSession {
    enum State { INIT, ACTION, CHECK, END, ERROR }

    Question action();
    boolean check(String answer);
    String end();
    State getState();
    void setState(State state);  // Добавляем отсутствующий метод
}