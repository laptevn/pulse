package com.pulse.request;

import com.pulse.RequestContext;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;

public class RegisterRequestHandler implements RequestHandler {
    @Override
    public boolean handle(Message message, RequestContext context) {
        if (message.hasText() && "/register".equals(message.getText())) {
            context.getRegisteredUsers().add(message.getChatId());

            SendMessage sendMessageRequest = new SendMessage();
            sendMessageRequest.setChatId(message.getChatId().toString());
            sendMessageRequest.setText("Вы успешно зарегестрированы");

            try {
                context.getSender().sendMessage(sendMessageRequest);
            } catch (TelegramApiException e) {
            }
            return true;
        }

        return false;
    }
}