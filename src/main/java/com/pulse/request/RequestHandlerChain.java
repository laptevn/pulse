package com.pulse.request;

import com.pulse.RequestContext;
import org.telegram.telegrambots.api.objects.Message;

import java.util.List;

public class RequestHandlerChain implements RequestHandler {
    private final List<RequestHandler> handlers;

    public RequestHandlerChain(List<RequestHandler> handlers) {
        this.handlers = handlers;
    }

    @Override
    public boolean handle(Message message, RequestContext context) {
        return handlers.stream().anyMatch(handler -> handler.handle(message, context));
    }
}