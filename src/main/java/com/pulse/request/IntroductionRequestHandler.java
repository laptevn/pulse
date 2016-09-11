package com.pulse.request;

import com.pulse.RequestContext;
import org.telegram.telegrambots.api.objects.Message;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class IntroductionRequestHandler implements RequestHandler {
    private static final Set<String> INTRODUCTION_REQUEST = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("/start", "/")));
    private String MESSAGE_RESPONSE;

    public IntroductionRequestHandler(String introductionMessage) {
        MESSAGE_RESPONSE = introductionMessage;
    }

    @Override
    public boolean handle(Message message, RequestContext context) {
        if (message.hasText() && INTRODUCTION_REQUEST.contains(message.getText())) {
            context.getSender().send(message.getChatId().toString(), MESSAGE_RESPONSE);
            return true;
        }
        return false;
    }
}