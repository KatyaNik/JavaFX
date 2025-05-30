package org.example;

import org.example.commands.Command;
import org.example.commands.ExamCommand;
import org.example.commands.LearnCommand;
import org.example.commands.StudyCommand;
import org.example.senders.Sender;
import org.example.sessions.StateSession;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    private final String BOT_TOKEN = "7850984525:AAGMkcTX1a1vCdbdMC3ja191pWbmsCL1Eyc";
    private final String BOT_NAME = "ExaminatorBot";
    private final Map<Long, Sender> userSessions = new HashMap<>();

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }

        Message msg = update.getMessage();
        long chatId = msg.getChatId();
        String text = msg.getText().trim();

        try {
            SendMessage response = handleMessage(chatId, text);
            execute(response);
        } catch (Exception e) {
            handleError(chatId, e);
        }
    }

    private SendMessage handleMessage(long chatId, String text) throws TelegramApiException {
        SendMessage response = new SendMessage();
        response.setChatId(String.valueOf(chatId));

        if (text.equals("/start")) {
            response.setText("Выберите режим:\n/exam - тест\n/study - обучение\n/learn - практика");
        }
        else if (text.equals("/study")) {
            Command cmd = new StudyCommand();
            Sender sender = cmd.execute(response);
            userSessions.put(chatId, sender);
            response = sender.createSendMessage(); // Получаем первый вопрос
        }
        else if (text.equals("/learn")) {
            Command cmd = new LearnCommand();
            Sender sender = cmd.execute(response);
            userSessions.put(chatId, sender);
            response = sender.createSendMessage(); // Получаем первый вопрос
        }
        else if (text.startsWith("/exam")) {
            return handleExamMode(chatId, response);
        }
        else if (userSessions.containsKey(chatId)) {
            Sender sender = userSessions.get(chatId);
            sender.onMessageReceived(text);
            response = sender.createSendMessage();
        }
        else {
            response.setText("Неизвестная команда. Введите /start");
        }

        // Гарантируем непустой текст
        if (response.getText() == null || response.getText().isEmpty()) {
            response.setText("Ошибка системы. Попробуйте еще раз.");
        }

        return response;
    }

    private SendMessage createStartMessage(SendMessage response) {
        response.setText("Выберите режим:\n/exam - тест\n/study - обучение\n/learn - практика");
        return response;
    }

    private SendMessage handleExamMode(long chatId, SendMessage response) throws TelegramApiException {
        Command cmd = new ExamCommand(5);
        Sender sender = cmd.execute(response);
        userSessions.put(chatId, sender);
        return sender.createSendMessage(); // Получаем первый вопрос сразу
    }

    private SendMessage handleStudyMode(long chatId, SendMessage response) throws TelegramApiException {
        Command cmd = new StudyCommand();
        Sender sender = cmd.execute(response);
        userSessions.put(chatId, sender);
        return sender.createSendMessage(); // Получаем первый вопрос сразу
    }

    private SendMessage handleLearnMode(long chatId, SendMessage response) throws TelegramApiException {
        Command cmd = new LearnCommand();
        Sender sender = cmd.execute(response);
        userSessions.put(chatId, sender);
        return sender.createSendMessage(); // Получаем первый вопрос сразу
    }

    private SendMessage handleUserResponse(long chatId, String text, SendMessage response) throws TelegramApiException {
        Sender sender = userSessions.get(chatId);
        sender.onMessageReceived(text);
        SendMessage newResponse = sender.createSendMessage();

        if (sender.getState() == StateSession.State.END) {
            userSessions.remove(chatId);
        }

        return ensureValidResponse(newResponse);
    }

    private SendMessage ensureValidResponse(SendMessage response) {
        if (response.getText() == null || response.getText().isEmpty()) {
            response.setText("Следующий вопрос готовится...");
        }
        return response;
    }

    private void handleError(long chatId, Exception e) {
        e.printStackTrace();
        SendMessage errorMsg = new SendMessage();
        errorMsg.setChatId(String.valueOf(chatId));
        errorMsg.setText("Произошла ошибка при обработке запроса. Попробуйте еще раз.");

        try {
            execute(errorMsg);
        } catch (TelegramApiException ex) {
            ex.printStackTrace();
        }
    }
}