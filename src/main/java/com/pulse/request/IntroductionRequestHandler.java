package com.pulse.request;

import com.pulse.RequestContext;
import org.telegram.telegrambots.api.objects.Message;

public class IntroductionRequestHandler implements RequestHandler {
    @Override
    public boolean handle(Message message, RequestContext context) {
        /*
        TODO:
        1. Handle "/start" and "/" requests here
        2. Send AboutMe information and explanation of each supported command in response.
         */
        return false;
    }
}