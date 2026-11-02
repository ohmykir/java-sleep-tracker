package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class SleepingSession {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final SleepQuality quality;

    public SleepingSession(LocalDateTime startTime, LocalDateTime endTime, SleepQuality quality) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.quality = quality;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public SleepQuality getQuality() {
        return quality;
    }

    public boolean isNightSession() {
        if (startTime.toLocalDate().equals(endTime.toLocalDate())) {
            return startTime.toLocalTime().isBefore(LocalTime.of(6, 0, 0));
        }

        return !endTime.toLocalTime().equals(LocalTime.MIDNIGHT);
    }

    public Chronotype getChronotype() throws IllegalCallerException {
        if (!isNightSession()) {
            throw new IllegalCallerException();
        }

        if  (startTime.toLocalDate().equals(endTime.toLocalDate())) {
            if (endTime.toLocalTime().isBefore(LocalTime.of(9, 0, 0))) {
                return Chronotype.PIGEON;
            }

            return Chronotype.OWL;
        } else {
            if (startTime.toLocalTime().isBefore(LocalTime.of(22, 0, 0)) &&
                endTime.toLocalTime().isBefore(LocalTime.of(7, 0, 0))) {
                return Chronotype.LARK;
            } else if (startTime.toLocalTime().isAfter(LocalTime.of(23, 0, 0)) &&
                       endTime.toLocalTime().isAfter(LocalTime.of(9, 0, 0))) {
                return Chronotype.OWL;
            } else {
                return Chronotype.PIGEON;
            }
        }
    }
}