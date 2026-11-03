package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.function.DetermineChronotype;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.yandex.practicum.sleeptracker.TestData.session;

public class DetermineChronotypeTest {
    private final DetermineChronotype function = new DetermineChronotype();

    private static SleepingSession owl(String day, String nextDay) {
        return session(day + " 23:30", nextDay + " 09:30");
    }

    private static SleepingSession lark(String day, String nextDay) {
        return session(day + " 21:00", nextDay + " 06:00");
    }

    private static SleepingSession pigeon(String day, String nextDay) {
        return session(day + " 22:30", nextDay + " 07:30");
    }

    @Test
    void emptyListGivesPigeon() {
        assertEquals(Chronotype.PIGEON, function.run(List.of()).getResult());
    }

    @Test
    void onlyDaytimeSessionsGivePigeon() {
        List<SleepingSession> sessions = List.of(
                session("03.10.25 14:30", "03.10.25 15:20"),
                session("04.10.25 13:00", "04.10.25 14:00"));

        assertEquals(Chronotype.PIGEON, function.run(sessions).getResult());
    }

    @Test
    void mostlyOwlNightsGiveOwl() {
        List<SleepingSession> sessions = List.of(
                owl("01.10.25", "02.10.25"),
                owl("02.10.25", "03.10.25"),
                lark("03.10.25", "04.10.25"));

        assertEquals(Chronotype.OWL, function.run(sessions).getResult());
    }

    @Test
    void mostlyLarkNightsGiveLark() {
        List<SleepingSession> sessions = List.of(
                lark("01.10.25", "02.10.25"),
                lark("02.10.25", "03.10.25"),
                pigeon("03.10.25", "04.10.25"));

        assertEquals(Chronotype.LARK, function.run(sessions).getResult());
    }

    @Test
    void mostlyPigeonNightsGivePigeon() {
        List<SleepingSession> sessions = List.of(
                pigeon("01.10.25", "02.10.25"),
                pigeon("02.10.25", "03.10.25"),
                owl("03.10.25", "04.10.25"));

        assertEquals(Chronotype.PIGEON, function.run(sessions).getResult());
    }

    @Test
    void tieBetweenOwlAndLarkGivesPigeon() {
        List<SleepingSession> sessions = List.of(
                owl("01.10.25", "02.10.25"),
                lark("02.10.25", "03.10.25"));

        assertEquals(Chronotype.PIGEON, function.run(sessions).getResult());
    }

    @Test
    void daytimeSessionsAreIgnored() {
        List<SleepingSession> sessions = List.of(
                owl("01.10.25", "02.10.25"),
                session("02.10.25 13:00", "02.10.25 14:00"),
                session("03.10.25 13:00", "03.10.25 14:00"));

        assertEquals(Chronotype.OWL, function.run(sessions).getResult());
    }
}