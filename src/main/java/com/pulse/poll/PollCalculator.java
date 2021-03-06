package com.pulse.poll;

import com.pulse.QuestionContext;

import java.util.*;
import java.util.stream.Collectors;

public class PollCalculator {
    private final QuestionContext questionContext;
    private final int numberOfRegisteredUsers;
    private int numberOfUsersWithAnswers;
    private final Set<String> usersWithCorrectAnswers = new HashSet<>();
    private final Map<Integer, Integer> answerToCount = new HashMap<>();
    private final ChartFactory chartFactory;

    public PollCalculator(QuestionContext questionContext, int numberOfRegisteredUsers, ChartFactory chartFactory) {
        this.questionContext = questionContext;
        this.numberOfRegisteredUsers = numberOfRegisteredUsers;
        this.chartFactory = chartFactory;
    }

    public void updateState(String userId, int answer) {
        numberOfUsersWithAnswers++;
        if (answer == questionContext.getCorrectAnswerIndex() + 1) {
            usersWithCorrectAnswers.add(userId);
        }

        if (!answerToCount.containsKey(answer)) {
            answerToCount.put(answer, 0);
        }
        answerToCount.put(answer, answerToCount.get(answer) + 1);
    }

    public boolean allUsersGaveAnswers() {
        return (numberOfRegisteredUsers - numberOfUsersWithAnswers) <= 0;
    }

    public String calculateGeneralResult() {
        int percent = numberOfUsersWithAnswers == 0 ? 0 : usersWithCorrectAnswers.size() * 100 / numberOfUsersWithAnswers;

        StringBuilder result = new StringBuilder();
        result.append("Опрос окончен. Всего ")
                .append(numberOfUsersWithAnswers)
                .append(" человек из ")
                .append(numberOfRegisteredUsers)
                .append(" поучаствовали в опросе.")
                .append(System.lineSeparator());
        result.append("Было дано ").append(percent).append("% правильных ответов.");

        for (AnswerCountPair pair : getAnswersSortedByCount()) {
            result.append(System.lineSeparator());
            result.append(pair.getCount()).append(" человек выбрали вариант №").append(pair.getAnswer());
        }

        return result.toString();
    }

    public byte[] calculateGeneralChart() {
        return chartFactory.createBarChart(answerToCount.entrySet().stream()
                .map(entry -> new AnswerCountPair(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));
    }

    private List<AnswerCountPair> getAnswersSortedByCount() {
        return answerToCount.entrySet().stream()
                .map(entry -> new AnswerCountPair(entry.getKey(), entry.getValue()))
                .sorted()
                .collect(Collectors.toList());
    }

    public String calculatePersonalResult(String userId) {
        return usersWithCorrectAnswers.contains(userId) ? "Поздравляем, вы дали правильный ответ." : "Вы не дали правильный ответ.";
    }
}