package com.pulse.request;

import com.pulse.BotProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class RequestHandlerChainFactory {
    @Bean
    @Autowired
    public RequestHandler create(BotProperties botProperties) {
        return new RequestHandlerChain(Arrays.asList(
                new RegisterRequestHandler(),
                new PollRequestHandler(),
                new AnswerRequestHandler(),
                new IntroductionRequestHandler(botProperties.getIntroductionMessage()),
                new PollRequestHandler()));
    }
}