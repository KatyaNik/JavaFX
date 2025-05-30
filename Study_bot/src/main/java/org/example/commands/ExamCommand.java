package org.example.commands;

import org.example.senders.ExamSender;
import org.example.senders.Sender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class ExamCommand implements Command {
    private final int questionCount;

    public ExamCommand(int count) {
        this.questionCount = count;
    }

    @Override
    public Sender execute(SendMessage message) {
        long chatId = Long.parseLong(message.getChatId());
        return new ExamSender(chatId, questionCount);
    }
}