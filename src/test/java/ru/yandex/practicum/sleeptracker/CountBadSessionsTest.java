package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.CountBadSessions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.TestData.session;

public class CountBadSessionsTest {
    private final CountBadSessions function = new CountBadSessions();

    @Test
    void emptyListGivesZero() {
        assertEquals(0, function.run(List.of()).getResult());
    }

    @Test
    void noBadSessionsGivesZero() {
        List<SleepingSession> sessions = List.of(
                session("01.10.25 23:00", "02.10.25 07:00", SleepQuality.GOOD),
                session("02.10.25 23:00", "03.10.25 07:00", SleepQuality.NORMAL));

        assertEquals(0, function.run(sessions).getResult());
    }

    @Test
    void countsOnlyBadSessions() {
        List<SleepingSession> sessions = List.of(
                session("01.10.25 23:00", "02.10.25 07:00", SleepQuality.GOOD),
                session("02.10.25 23:00", "03.10.25 07:00", SleepQuality.BAD),
                session("03.10.25 23:00", "04.10.25 07:00", SleepQuality.NORMAL),
                session("04.10.25 23:00", "05.10.25 07:00", SleepQuality.BAD));

        assertEquals(2, function.run(sessions).getResult());
    }
}