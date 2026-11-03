package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.MaximumSessionDuration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.TestData.session;

public class MaximumSessionDurationTest {

    private final MaximumSessionDuration function = new MaximumSessionDuration();

    @Test
    void emptyListGivesZero() {
        assertEquals(0, function.run(List.of()).getResult());
    }

    @Test
    void singleSessionGivesItsDuration() {
        List<SleepingSession> sessions = List.of(session("01.10.25 23:00", "02.10.25 07:00"));

        assertEquals(480, function.run(sessions).getResult());
    }

    @Test
    void findsLongestSession() {
        List<SleepingSession> sessions = List.of(
                session("01.10.25 23:00", "02.10.25 07:00"),   // 480
                session("03.10.25 14:00", "03.10.25 14:45"),   // 45
                session("03.10.25 23:30", "04.10.25 06:30"));  // 420

        assertEquals(480, function.run(sessions).getResult());
    }
}