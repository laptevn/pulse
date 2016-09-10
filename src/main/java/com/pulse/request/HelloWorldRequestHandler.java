package com.pulse.request;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;

public class HelloWorldRequestHandler implements RequestHandler {
    @Override
    public boolean handle(Message message, AbsSender sender) {
        if (message.hasText() && "/hw".equals(message.getText())) {
            SendMessage sendMessageRequest = new SendMessage();
            sendMessageRequest.setChatId(message.getChatId().toString());
            sendMessageRequest.setText("Hello world");

            try {
                sender.sendMessage(sendMessageRequest);
            } catch (TelegramApiException e) {
            }
            return true;
        }

        return false;
    }
}