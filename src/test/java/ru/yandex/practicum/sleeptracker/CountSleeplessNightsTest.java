package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.CountSleeplessNights;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.TestData.session;

public class CountSleeplessNightsTest {
    private final CountSleeplessNights function = new CountSleeplessNights();

    @Test
    void emptyListGivesZero() {
        assertEquals(0, function.run(List.of()).getResult());
    }

    @Test
    void singleNightSessionGivesZero() {
        List<SleepingSession> sessions = List.of(session("01.10.25 23:00", "02.10.25 07:00"));

        assertEquals(0, function.run(sessions).getResult());
    }

    @Test
    void everyNightCoveredGivesZero() {
        List<SleepingSession> sessions = List.of(
                session("01.10.25 22:15", "02.10.25 08:00"),
                session("02.10.25 23:00", "03.10.25 08:00"),
                session("03.10.25 14:30", "03.10.25 15:20"),
                session("03.10.25 23:30", "04.10.25 06:20"));

        assertEquals(0, function.run(sessions).getResult());
    }

    @Test
    void skippedNightBetweenTwoSessionsIsCounted() {
        List<SleepingSession> sessions = List.of(
                session("01.10.25 23:00", "02.10.25 07:00"),
                session("03.10.25 23:00", "04.10.25 07:00"));

        assertEquals(1, function.run(sessions).getResult());
    }

    @Test
    void sessionEndingExactlyAtMidnightDoesNotCoverTheNight() {
        List<SleepingSession> sessions = List.of(session("01.10.25 20:00", "02.10.25 00:00"));

        assertEquals(1, function.run(sessions).getResult());
    }

    @Test
    void onlyDaytimeSessionOnOneDayGivesZero() {
        List<SleepingSession> sessions = List.of(session("10.10.25 13:00", "10.10.25 14:30"));

        assertEquals(0, function.run(sessions).getResult());
    }

    @Test
    void firstSessionStartingBefore12CountsPreviousNight() {
        List<SleepingSession> sessions = List.of(
                session("03.10.25 11:59", "03.10.25 13:00"),
                session("03.10.25 23:00", "04.10.25 07:00"));

        assertEquals(1, function.run(sessions).getResult());
    }
}