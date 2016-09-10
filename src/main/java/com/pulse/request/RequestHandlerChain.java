package com.pulse.request;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;

import java.util.List;

public class RequestHandlerChain implements RequestHandler {
    private final List<RequestHandler> handlers;

    public RequestHandlerChain(List<RequestHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public boolean handle(Message message, AbsSender sender) {
        return handlers.stream().anyMatch(handler -> handler.handle(message, sender));
    }
}