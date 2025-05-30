package org.example.senders;

import org.example.sessions.StateSession;
import org.example.model.Question;
import org.example.sessions.StudySession;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public class StudySender extends Sender {
    private boolean waitingForAnswer = false;

    public StudySender(long chatId) {
        super(chatId);
        this.stateSession = new StudySession();
    }

    @Override
    public void onMessageReceived(String message) {
        if (waitingForAnswer) {
            stateSession.check(message);
            waitingForAnswer = false;
        }
    }

    @Override
    public SendMessage createSendMessage() {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));

        if (!waitingForAnswer) {
            Question question = stateSession.action();
            String text = "Вопрос:\n" + question.getQuestion() + "\n\n" +
                    formatOptions(question.getAnswerOptions()) +
                    "\n\nОтправьте ваш ответ";
            message.setText(text);
            waitingForAnswer = true;
        } else {
            message.setText(stateSession.end() + "\n\nОтправьте любое сообщение для следующего вопроса");
            waitingForAnswer = false;
        }

        return message;
    }

    private String formatOptions(List<String> options) {
        StringBuilder sb = new StringBuilder("Варианты:\n");
        for (int i = 0; i < options.size(); i++) {
            sb.append(i+1).append(". ").append(options.get(i)).append("\n");
        }
        return sb.toString();
    }
}