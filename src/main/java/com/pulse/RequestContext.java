package com.pulse;

import java.util.List;

public class RequestContext {
    private final MessageSender sender;
    private final List<Long> registeredUsers;

    public RequestContext(MessageSender sender, List<Long> registeredUsers) {
        this.sender = sender;
        this.registeredUsers = registeredUsers;
    }

    public MessageSender getSender() {
        return sender;
    }

    public List<Long> getRegisteredUsers() {
        return registeredUsers;
    }
}