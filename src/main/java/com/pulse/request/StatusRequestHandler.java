package com.pulse.request;

import com.pulse.RequestContext;
import org.telegram.telegrambots.api.objects.Message;

public class StatusRequestHandler implements RequestHandler {
    @Override
    public boolean handle(Message message, RequestContext context) {
        if (message.hasText() && "/status".equals(message.getText())) {
            context.getSender().send(message.getChatId().toString(), calculateStatus(context));
            return true;
        }

        return false;
    }

    private String calculateStatus(RequestContext context) {
        return String.format(
                "В системе зарегистрировано %d пользователй.%n%s", context.getRegisteredUsers().size(), context.getPollSession().getStatus());
    }
}