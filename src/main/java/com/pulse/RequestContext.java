package com.pulse;

import org.telegram.telegrambots.bots.AbsSender;

import java.util.List;

public class RequestContext {
    private final AbsSender sender;
    private final List<Long> registeredUsers;

    public RequestContext(AbsSender sender, List<Long> registeredUsers) {
        this.sender = sender;
        this.registeredUsers = registeredUsers;
    }

    public AbsSender getSender() {
        return sender;
    }

    public List<Long> getRegisteredUsers() {
        return registeredUsers;
    }
}