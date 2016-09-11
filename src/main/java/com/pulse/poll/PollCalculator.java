package com.pulse.poll;

import com.pulse.QuestionContext;

import java.util.HashSet;
import java.util.Set;

public class PollCalculator {
    private final QuestionContext questionContext;
    private final int numberOfRegisteredUsers;
    private int numberOfUsersWithAnswers;
    private final Set<String> usersWithCorrectAnswers = new HashSet<>();

    public PollCalculator(QuestionContext questionContext, int numberOfRegisteredUsers) {
        this.questionContext = questionContext;
        this.numberOfRegisteredUsers = numberOfRegisteredUsers;
    }

    public void updateState(String userId, int answer) {
        numberOfUsersWithAnswers++;
        if (answer == questionContext.getCorrectAnswerIndex() + 1) {
            usersWithCorrectAnswers.add(userId);
        }
    }

    public boolean allUsersGaveAnswers() {
        return (numberOfRegisteredUsers - numberOfUsersWithAnswers) <= 0;
    }

    public String calculateGeneralResult() {
        int percent = numberOfUsersWithAnswers == 0 ? 0 : usersWithCorrectAnswers.size() * 100 / numberOfUsersWithAnswers;
        return String.format(
                "Опрос окончен. Всего %d человек из %d поучаствовали в опросе.%nБыло дано %d%% правильных ответов.",
                numberOfUsersWithAnswers,
                numberOfRegisteredUsers,
                percent);
    }

    public String calculatePersonalResult(String userId) {
        return usersWithCorrectAnswers.contains(userId) ? "Поздравляем, вы дали правильный ответ." : "Вы не дали правильный ответ.";
    }
}