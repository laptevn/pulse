package com.pulse;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.AbsSender;

public class MessageSender {
    private final AbsSender sender;

    public MessageSender(AbsSender sender) {
        this.sender = sender;
    }

    public void send(String chatId, String messageText) {
        SendMessage sendMessageRequest = new SendMessage();
        sendMessageRequest.setChatId(chatId);
        sendMessageRequest.setText(messageText);

        send(sendMessageRequest);
    }

    public void send(SendMessage message) {
        try {
            sender.sendMessage(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}