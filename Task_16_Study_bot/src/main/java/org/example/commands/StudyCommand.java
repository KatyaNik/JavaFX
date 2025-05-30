package org.example.commands;

import org.example.senders.StudySender;
import org.example.senders.Sender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class StudyCommand implements Command {
    @Override
    public Sender execute(SendMessage message) {
        long chatId = Long.parseLong(message.getChatId());
        return new StudySender(chatId);
    }
}