package com.pulse;

import java.util.List;

public class RequestContext {
    private final MessageSender sender;
    private final List<Long> registeredUsers;
    private final PollSession pollSession;

    public RequestContext(MessageSender sender, List<Long> registeredUsers, PollSession pollSession) {
        this.sender = sender;
        this.registeredUsers = registeredUsers;
        this.pollSession = pollSession;
    }

    public MessageSender getSender() {
        return sender;
    }

    public List<Long> getRegisteredUsers() {
        return registeredUsers;
    }

    public PollSession getPollSession() {
        return pollSession;
    }
}