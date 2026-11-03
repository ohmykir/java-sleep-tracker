package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class CountSleeplessNights implements SleepAnalysisFunction<Integer>  {

    @Override
    public SleepAnalysisResult<Integer>  run(List<SleepingSession> sessions) {
        int res = 0;
        if (!sessions.isEmpty()) {
            int nightSessions = (int) sessions.stream().filter(SleepingSession::isNightSession)
                                                       .count();

            LocalDateTime start = sessions.getFirst().getStartTime();
            LocalDateTime end = sessions.getLast().getEndTime();

            res = countTotalNights(start, end) - nightSessions;
        }

        return new SleepAnalysisResult<>(res, "Количество бессонных ночей");
    }

    private int countTotalNights(LocalDateTime start, LocalDateTime end) {
        LocalDate firstDay = start.toLocalTime().isBefore(LocalTime.NOON)
                ? start.toLocalDate().minusDays(1)
                : start.toLocalDate();

        return (int) ChronoUnit.DAYS.between(firstDay, end.toLocalDate());
    }
}