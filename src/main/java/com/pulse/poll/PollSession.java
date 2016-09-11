package com.pulse.poll;

import com.pulse.MessageSender;
import com.pulse.QuestionContext;
import com.pulse.QuestionToMessageMapper;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class PollSession {
    private final static long POLL_DURATION = TimeUnit.SECONDS.toMillis(60);

    private final List<QuestionContext> questionContexts;
    private int currentQuestionIndex = -1;
    private final MessageSender sender;
    private final QuestionToMessageMapper questionToMessageMapper;
    private String pollOwner;
    private boolean isRunning;
    private Timer timer;
    private PollCalculator pollCalculator;
    private Set<String> registeredUsers;

    public PollSession(List<QuestionContext> questionContexts, MessageSender sender, QuestionToMessageMapper questionToMessageMapper) {
        if (questionContexts.isEmpty()) {
            throw new IllegalArgumentException("Cannot create a poll with no questions");
        }

        this.questionContexts = questionContexts;
        this.sender = sender;
        this.questionToMessageMapper = questionToMessageMapper;
    }

    public synchronized void startPolling(Collection<String> users, String pollOwner) {
        this.pollOwner = pollOwner;
        isRunning = true;

        currentQuestionIndex++;
        if (currentQuestionIndex >= questionContexts.size()) {
            currentQuestionIndex = 0;
        }

        registeredUsers = new HashSet<>(users);
        questionToMessageMapper.map(getCurrentQuestion(), registeredUsers).forEach(sender::send);

        pollCalculator = new PollCalculator(getCurrentQuestion(), registeredUsers.size());

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                stopPolling();
            }
        }, POLL_DURATION);
    }

    private QuestionContext getCurrentQuestion() {
        return questionContexts.get(currentQuestionIndex);
    }

    private synchronized void stopPolling() {
        if (!isRunning) {
            return;
        }

        sendPollingResults();

        isRunning = false;
        timer.cancel();
    }

    private void sendPollingResults() {
        String generalResult = pollCalculator.calculateGeneralResult();
        sender.send(pollOwner, generalResult);

        registeredUsers.forEach(
                chatId -> sender.send(chatId, String.format("%s%n%s", generalResult, pollCalculator.calculatePersonalResult(chatId))));
    }

    public synchronized boolean isRunning() {
        return isRunning;
    }

    public boolean isAnswerValid(int answer) {
        return answer > 0 && answer <= getCurrentQuestion().getAnswers().size();
    }

    public synchronized void handleAnswer(String userId, int answer) {
        if (isRunning) {
            pollCalculator.updateState(userId, answer);
            if (pollCalculator.allUsersGaveAnswers()) {
                stopPolling();
            }
        }
    }
}