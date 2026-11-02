package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

public interface SleepAnalysisFunction<T> {
    SleepAnalysisResult<T> run(List<SleepingSession> sessions);
}