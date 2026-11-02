package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;

public class MaximumSessionDuration implements SleepAnalysisFunction<Integer>  {

    @Override
    public SleepAnalysisResult<Integer>  run(List<SleepingSession> sessions) {
        int ans = sessions.stream().map(s -> Duration.between(s.getStartTime(), s.getEndTime()))
                .max(Duration::compareTo)
                .map(Duration::toMinutes)
                .orElse(0L)
                .intValue();

        return new SleepAnalysisResult<>(ans, "Максимальная продолжительность сессии (в минутах)");
    }
}