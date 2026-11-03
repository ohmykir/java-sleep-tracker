package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

public class CountSessions implements SleepAnalysisFunction<Integer> {
    public SleepAnalysisResult<Integer>  run(List<SleepingSession> sessions) {
        return new SleepAnalysisResult<>(sessions.size(), "Количество сессий за период");
    }
}