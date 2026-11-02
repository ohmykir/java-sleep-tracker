package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;

public class AverageSessionDuration implements SleepAnalysisFunction<Integer> {

    @Override
    public SleepAnalysisResult<Integer> run(List<SleepingSession> sessions) {
        int ans = 0;
        if (!sessions.isEmpty()) {
            int sum = sessions.stream().map(s -> Duration.between(s.getStartTime(), s.getEndTime()).toMinutes())
                    .mapToInt(Long::intValue)
                    .sum();
            ans = sum / sessions.size();
        }

        return new SleepAnalysisResult<>(ans, "Средняя продолжительность сессии (в минутах)");
    }
}