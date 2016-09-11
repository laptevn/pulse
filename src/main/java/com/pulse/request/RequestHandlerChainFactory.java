package com.pulse.request;

import com.pulse.BotProperties;

import java.util.Arrays;

public class RequestHandlerChainFactory {
    public RequestHandlerChain create(BotProperties botProperties) {
        return new RequestHandlerChain(Arrays.asList(
                new RegisterRequestHandler(),
                new PollRequestHandler(),
                new AnswerRequestHandler(),
                new IntroductionRequestHandler(botProperties.getIntroductionMessage()),
                new PollRequestHandler(),
                new AnswerRequestHandler()));
    }
}