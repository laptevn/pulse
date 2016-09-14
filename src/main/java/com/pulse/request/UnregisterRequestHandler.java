package com.pulse.request;

import com.pulse.RequestContext;
import org.telegram.telegrambots.api.objects.Message;

public class UnregisterRequestHandler implements RequestHandler {
    @Override
    public boolean handle(Message message, RequestContext context) {
        if (message.hasText() && "/unregister".equals(message.getText())) {
            String messageText = context.getRegisteredUsers().remove(message.getChatId().toString())
                    ? "Ваша регистрация была удалена."
                    : "Вы не были зарегестрированы.";

            context.getSender().send(message.getChatId().toString(), messageText);
            return true;
        }

        return false;
    }
}