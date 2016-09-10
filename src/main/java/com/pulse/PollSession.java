package com.pulse;

import java.util.List;

// TODO: Think of thread safety.
public class PollSession {
    private final List<QuestionContext> questionContexts; // TODO: Add a support of traversing through this list.
    private final MessageSender sender;
    private final QuestionToMessageMapper questionToMessageMapper;
    private String pollOwner;
    private boolean isRunning;

    public PollSession(List<QuestionContext> questionContexts, MessageSender sender, QuestionToMessageMapper questionToMessageMapper) {
        if (questionContexts.isEmpty()) {
            throw new IllegalArgumentException("Cannot create a poll with no questions");
        }

        this.questionContexts = questionContexts;
        this.sender = sender;
        this.questionToMessageMapper = questionToMessageMapper;
    }

    public void start(List<String> registeredUsers, String pollOwner) {
        this.pollOwner = pollOwner;
        isRunning = true;

        questionToMessageMapper.map(questionContexts.get(0), registeredUsers).forEach(message -> sender.send(message));
    }

    public boolean isRunning() {
        return isRunning;
    }
}