package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;

public class CountBadSessions implements SleepAnalysisFunction<Integer> {

    public SleepAnalysisResult<Integer> run(List<SleepingSession> sessions) {
        int res = (int) sessions.stream().filter(s -> s.getQuality() == SleepQuality.BAD).count();

        return new SleepAnalysisResult<>(res, "Количество плохих сессий за период");
    }
}