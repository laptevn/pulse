package com.pulse.request;

import com.pulse.RequestContext;
import org.telegram.telegrambots.api.objects.Message;

public class PollRequestHandler implements RequestHandler {
    @Override
    public boolean handle(Message message, RequestContext context) {
        if (message.hasText() && "/poll".equals(message.getText())) {
            //context.getRegisteredUsers().add(message.getChatId());
            context.getSender().send(message.getChatId().toString(), "Опрос начат");
            return true;
        }

        return false;
    }
}