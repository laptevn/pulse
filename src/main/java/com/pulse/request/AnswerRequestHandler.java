package com.pulse.request;

import com.pulse.RequestContext;
import org.apache.commons.lang.math.NumberUtils;
import org.telegram.telegrambots.api.objects.Message;

public class AnswerRequestHandler implements RequestHandler {
    @Override
    public boolean handle(Message message, RequestContext context) {
        if (message.hasText() && context.getPollSession().isRunning() && NumberUtils.isNumber(message.getText())) {
            String messageText;
            int answer = NumberUtils.toInt(message.getText(), -1);
            if (context.getPollSession().isAnswerValid(answer)) {
                context.getPollSession().handleAnswer(message.getChatId().toString(), answer);
                messageText = "Спасибо. Ваш ответ учтен.";
            } else {
                messageText = "Ответ должен быть выбран из числа вариантов";
            }

            context.getSender().send(message.getChatId().toString(), messageText);
            return true;
        }

        return false;
    }
}