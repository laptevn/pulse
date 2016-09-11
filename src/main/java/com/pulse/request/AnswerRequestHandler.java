package com.pulse.request;

import com.pulse.RequestContext;
import org.apache.commons.lang.math.NumberUtils;
import org.telegram.telegrambots.api.objects.Message;

public class AnswerRequestHandler implements RequestHandler {
    @Override
    public boolean handle(Message message, RequestContext context) {
        if (message.hasText() && context.getPollSession().isRunning() && NumberUtils.isNumber(message.getText())) {
            int answer = NumberUtils.toInt(message.getText(), -1);
            if (context.getPollSession().isAnswerValid(answer)) {
                context.getSender().send(message.getChatId().toString(), "Спасибо. Ваш ответ учтен.");
                context.getPollSession().handleAnswer(message.getChatId().toString(), answer);
            } else {
                context.getSender().send(message.getChatId().toString(), "Ответ должен быть выбран из числа вариантов");
            }

            return true;
        }

        return false;
    }
}