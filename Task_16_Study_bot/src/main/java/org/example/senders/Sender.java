package org.example.senders;

import org.example.sessions.StateSession;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public abstract class Sender {
    protected StateSession stateSession;
    protected final long chatId;  // Переименовано в chatId (нижний регистр 'd')

    public Sender(long chatId) {  // Конструктор с параметром
        this.chatId = chatId;
    }

    public abstract void onMessageReceived(String message);
    public abstract SendMessage createSendMessage();

    public StateSession.State getState() {
        return stateSession.getState();
    }
}