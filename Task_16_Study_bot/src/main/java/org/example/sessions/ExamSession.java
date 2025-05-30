package org.example.sessions;

import org.example.model.Question;

public class ExamSession implements StateSession {
    private State state = State.INIT;

    @Override
    public Question action() {
        state = State.ACTION;
        return null;
    }

    @Override
    public boolean check(String answer) {
        return false;
    }

    @Override
    public String end() {
        state = State.END;
        return "";
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }
}