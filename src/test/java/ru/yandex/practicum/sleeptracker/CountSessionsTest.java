package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.CountSessions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.TestData.session;

public class CountSessionsTest {

    private final CountSessions function = new CountSessions();

    @Test
    void emptyListGivesZero() {
        assertEquals(0, function.run(List.of()).getResult());
    }

    @Test
    void oneSessionGivesOne() {
        assertEquals(1, function.run(List.of(session("01.10.25 23:00", "02.10.25 07:00"))).getResult());
    }

    @Test
    void countsAllSessionsIncludingDaytime() {
        List<SleepingSession> sessions = List.of(
                session("01.10.25 23:15", "02.10.25 07:30"),
                session("02.10.25 23:50", "03.10.25 06:40"),
                session("03.10.25 14:10", "03.10.25 15:00"));

        assertEquals(3, function.run(sessions).getResult());
    }
}
