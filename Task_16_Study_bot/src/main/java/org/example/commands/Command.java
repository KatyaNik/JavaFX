package org.example.commands;

import org.example.senders.Sender;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Command {
    Sender execute(SendMessage message);
}