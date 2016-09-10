package com.pulse.request;

import com.pulse.RequestContext;
import org.telegram.telegrambots.api.objects.Message;

public class RegisterRequestHandler implements RequestHandler {
    @Override
    public boolean handle(Message message, RequestContext context) {
        if (message.hasText() && "/register".equals(message.getText())) {
            context.getRegisteredUsers().add(message.getChatId());
            context.getSender().send(message.getChatId().toString(), "Вы успешно зарегестрированы");
            return true;
        }

        return false;
    }
}