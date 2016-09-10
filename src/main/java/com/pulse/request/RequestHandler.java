package com.pulse.request;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;

public interface RequestHandler {
    boolean handle(Message message, AbsSender sender);
}