package org.example.senders;

import org.example.sessions.LearnSession;
import org.example.sessions.StateSession;
import org.example.model.Question;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.List;

public class LearnSender extends Sender {
    private Question currentQuestion;
    private String lastAnswer;

    public LearnSender(long chatId) {
        super(chatId);
        this.stateSession = new LearnSession();
        this.currentQuestion = stateSession.action();
    }

    @Override
    public void onMessageReceived(String message) {
        this.lastAnswer = message; // Сохраняем последний ответ
    }

    @Override
    public SendMessage createSendMessage() {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));

        // Если это первый вопрос
        if (lastAnswer == null) {
            message.setText("Режим обучения (отвечайте до правильного ответа):\n\n" +
                    currentQuestion.getQuestion() + "\n\n" +
                    formatOptions(currentQuestion.getAnswerOptions()));
            return message;
        }

        // Проверяем ответ
        boolean isCorrect = stateSession.check(lastAnswer);

        if (isCorrect) {
            String result = "✅ Верно! " + stateSession.end();
            currentQuestion = stateSession.action(); // Новый вопрос
            message.setText(result + "\n\nНовый вопрос:\n" +
                    currentQuestion.getQuestion() + "\n\n" +
                    formatOptions(currentQuestion.getAnswerOptions()));
        } else {
            message.setText("❌ Неверно. Попробуйте еще раз:\n\n" +
                    currentQuestion.getQuestion() + "\n\n" +
                    formatOptions(currentQuestion.getAnswerOptions()));
        }

        lastAnswer = null; // Сбрасываем ответ для следующего сообщения
        return message;
    }

    private String formatOptions(List<String> options) {
        if (options == null || options.isEmpty()) {
            return "Варианты ответа отсутствуют";
        }

        StringBuilder sb = new StringBuilder("Варианты:\n");
        for (int i = 0; i < options.size(); i++) {
            sb.append(i + 1).append(". ").append(options.get(i)).append("\n");
        }
        return sb.toString();
    }
}