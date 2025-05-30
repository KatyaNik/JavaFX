package org.example.senders;

import org.example.model.BankQuestion;
import org.example.model.Question;
import org.example.sessions.ExamSession;
import org.example.sessions.StateSession;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import java.util.List;

public class ExamSender extends Sender {
    private final List<Question> examQuestions;
    private int currentQuestionIndex;
    private final int totalQuestions;
    private int correctAnswers;

    public ExamSender(long chatId, int questionCount) {
        super(chatId);  // Передаем chatId в родительский конструктор
        this.totalQuestions = questionCount;
        this.examQuestions = new BankQuestion().getQuestions(totalQuestions);
        this.currentQuestionIndex = 0;
        this.correctAnswers = 0;
        this.stateSession = new ExamSession();
    }

    @Override
    public void onMessageReceived(String message) {
        if (currentQuestionIndex < examQuestions.size()) {
            boolean isCorrect = examQuestions.get(currentQuestionIndex).checkAnswer(message);
            if (isCorrect) correctAnswers++;
            currentQuestionIndex++;

            if (currentQuestionIndex >= examQuestions.size()) {
                stateSession.setState(StateSession.State.END);
            }
        }
    }

    @Override
    public SendMessage createSendMessage() {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));

        String messageText;

        if (currentQuestionIndex >= examQuestions.size()) {
            messageText = "Экзамен завершен! Результат: " + correctAnswers + "/" + totalQuestions;
        } else {
            Question current = examQuestions.get(currentQuestionIndex);
            messageText = "Вопрос " + (currentQuestionIndex+1) + " из " + totalQuestions +
                    ":\n\n" + current.getQuestion() + "\n\n" +
                    formatOptions(current.getAnswerOptions());
        }

        message.setText(messageText);
        return message;
    }

    private String formatOptions(List<String> options) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < options.size(); i++) {
            sb.append(i + 1).append(". ").append(options.get(i)).append("\n");
        }
        return sb.toString();
    }
}