package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.yandex.practicum.sleeptracker.TestData.session;

public class SleepingSessionTest {

    @Test
    void sessionThroughMidnightIsNight() {
        assertTrue(session("01.10.25 23:15", "02.10.25 07:30").isNightSession());
    }

    @Test
    void sessionAfterMidnightIsNight() {
        assertTrue(session("05.10.25 00:10", "05.10.25 06:20").isNightSession());
    }

    @Test
    void sessionStartingJustBeforeSixIsNight() {
        assertTrue(session("05.10.25 05:59", "05.10.25 06:30").isNightSession());
    }

    @Test
    void sessionStartingExactlyAtSixIsNotNight() {
        assertFalse(session("05.10.25 06:00", "05.10.25 07:00").isNightSession());
    }

    @Test
    void daytimeSessionIsNotNight() {
        assertFalse(session("03.10.25 14:10", "03.10.25 15:00").isNightSession());
    }

    @Test
    void sessionEndingExactlyAtMidnightIsNotNight() {
        assertFalse(session("01.10.25 20:00", "02.10.25 00:00").isNightSession());
    }

    // ========================================================================

    @Test
    void startAfter23AndEndAfter9IsOwl() {
        assertEquals(Chronotype.OWL, session("01.10.25 23:30", "02.10.25 09:30").getChronotype());
    }

    @Test
    void startBefore23AndEndAfter7IsPigeon() {
        assertEquals(Chronotype.PIGEON, session("01.10.25 22:30", "02.10.25 08:30").getChronotype());
    }

    @Test
    void startAfter23AndEndBefore7IsPigeon() {
        assertEquals(Chronotype.PIGEON, session("01.10.25 23:30", "02.10.25 06:30").getChronotype());
    }

    @Test
    void startBefore22AndEndBefore7IsLark() {
        assertEquals(Chronotype.LARK, session("01.10.25 21:30", "02.10.25 06:30").getChronotype());
    }

    @Test
    void startBefore22AndEndAfter7IsPigeon() {
        assertEquals(Chronotype.PIGEON, session("01.10.25 21:30", "02.10.25 08:30").getChronotype());
    }

    @Test
    void startAfter22AndEndBefore7IsPigeon() {
        assertEquals(Chronotype.PIGEON, session("01.10.25 22:30", "02.10.25 06:30").getChronotype());
    }

    @Test
    void skipDaySession() {
        SleepingSession nap = session("03.10.25 14:10", "03.10.25 15:00");

        assertThrows(IllegalCallerException.class, nap::getChronotype);
    }
}
