package com.pulse.request;

import java.util.Arrays;

public class RequestHandlerChainFactory {
    public RequestHandlerChain create() {
        return new RequestHandlerChain(Arrays.asList(
                new RegisterRequestHandler(),
                new IntroductionRequestHandler(),
                new PollRequestHandler(),
                new AnswerRequestHandler()));
    }
}