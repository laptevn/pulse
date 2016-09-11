package com.pulse;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.bots.AbsSender;

import java.io.ByteArrayInputStream;

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

    public void sendImage(String chatId, byte[] imageData) {
        SendPhoto sendPhotoRequest = new SendPhoto();
        sendPhotoRequest.setChatId(chatId);
        sendPhotoRequest.setNewPhoto("image.png", new ByteArrayInputStream(imageData));

        try {
            sender.sendPhoto(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void send(SendMessage message) {
        try {
            sender.sendMessage(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}