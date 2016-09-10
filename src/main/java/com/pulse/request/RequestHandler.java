package com.pulse.request;

import com.pulse.RequestContext;
import org.telegram.telegrambots.api.objects.Message;

public interface RequestHandler {
    boolean handle(Message message, RequestContext context);
}