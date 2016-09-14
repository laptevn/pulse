package com.pulse.request;

import com.pulse.RequestContext;
import org.telegram.telegrambots.api.objects.Message;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class IntroductionRequestHandler  implements RequestHandler {
    private static final Set<String> INTRODUCTION_REQUEST = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("/start", "/")));
    private final String introductionMessage;

    public IntroductionRequestHandler(String introductionMessage) {
        this.introductionMessage = introductionMessage;
    }

    @Override
    public boolean handle(Message message, RequestContext context) {
        if (message.hasText() && INTRODUCTION_REQUEST.contains(message.getText())) {
            context.getSender().send(message.getChatId().toString(), introductionMessage);
            return true;
        }
        return false;
    }
}