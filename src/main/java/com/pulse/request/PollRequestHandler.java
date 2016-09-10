package com.pulse.request;

import com.pulse.RequestContext;
import org.telegram.telegrambots.api.objects.Message;

public class PollRequestHandler implements RequestHandler {
    @Override
    public boolean handle(Message message, RequestContext context) {
        if (message.hasText() && "/poll".equals(message.getText())) {
            String messageText;
            if (context.getRegisteredUsers().isEmpty()) {
                messageText = "Опрос не может быть начат. Нет зарегестрированных пользователей.";
            } else if (context.getPollSession().isRunning()) {
                messageText = "Опрос не может быть начат. Предыдущий опрос еще не окончен.";
            } else {
                context.getPollSession().start(context.getRegisteredUsers(), message.getChatId().toString());
                messageText = "Опрос начат";
            }

            context.getSender().send(message.getChatId().toString(), messageText);
            return true;
        }

        return false;
    }
}