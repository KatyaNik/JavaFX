package org.example.commands;

import org.example.senders.LearnSender;
import org.example.senders.Sender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class LearnCommand implements Command {
    @Override
    public Sender execute(SendMessage message) {
        long chatId = Long.parseLong(message.getChatId());
        return new LearnSender(chatId);  // Корректное создание с chatId
    }
}