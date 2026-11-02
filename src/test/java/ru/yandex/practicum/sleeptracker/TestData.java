package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestData {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    private TestData() {

    }

    public static SleepingSession session(String start, String end, SleepQuality quality) {
        return new SleepingSession(LocalDateTime.parse(start, FORMAT), LocalDateTime.parse(end, FORMAT), quality);
    }

    public static SleepingSession session(String start, String end) {
        return session(start, end, SleepQuality.NORMAL);
    }
}