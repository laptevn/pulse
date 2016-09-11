package com.pulse;

import com.pulse.poll.PollSession;

import java.util.Set;

public class RequestContext {
    private final MessageSender sender;
    private final Set<String> registeredUsers;
    private final PollSession pollSession;

    public RequestContext(MessageSender sender, Set<String> registeredUsers, PollSession pollSession) {
        this.sender = sender;
        this.registeredUsers = registeredUsers;
        this.pollSession = pollSession;
    }

    public MessageSender getSender() {
        return sender;
    }

    public Set<String> getRegisteredUsers() {
        return registeredUsers;
    }

    public PollSession getPollSession() {
        return pollSession;
    }
}