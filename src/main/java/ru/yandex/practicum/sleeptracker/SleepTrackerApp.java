package ru.yandex.practicum.sleeptracker;

import ru.yandex.practicum.sleeptracker.function.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SleepTrackerApp {

    static List<SleepAnalysisFunction<?>> functions = List.of(
            new CountSessions(), new MinimumSessionDuration(), new MaximumSessionDuration(),
            new AverageSessionDuration(), new CountBadSessions(), new CountSleeplessNights(),
            new DetermineChronotype()
    );

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java -jar sleeptracker.jar <path/to/log_filename>");
            System.exit(1);
        }
        String filename = args[0];

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            List<SleepingSession> sleepingSessions = reader.lines()
                                     .map(SleepTrackerApp::getSleepingSession)
                                     .toList();

            functions.forEach(function -> System.out.println(function.run(sleepingSessions)));
        } catch (IOException e) {
            System.out.println("Error opening file: " + filename);
        }
    }

    private static SleepingSession getSleepingSession(String input) {
        String[] split = input.split(";");
        LocalDateTime startTime = LocalDateTime.parse(split[0], DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        LocalDateTime endTime = LocalDateTime.parse(split[1], DateTimeFormatter.ofPattern("dd.MM.yy HH:mm"));
        SleepQuality sleepQuality = SleepQuality.valueOf(split[2]);

        return new SleepingSession(startTime, endTime, sleepQuality);
    }
}