package ru.yandex.practicum.sleeptracker.function;

import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.*;

public class DetermineChronotype implements SleepAnalysisFunction<Chronotype>  {

    @Override
    public SleepAnalysisResult<Chronotype>  run(List<SleepingSession> sessions) {
        Map<Chronotype, Integer> chronoMap = new HashMap<>();
        sessions.stream().filter(SleepingSession::isNightSession)
                         .forEach(session -> {
                             chronoMap.merge(session.getChronotype(), 1, Integer::sum);
                         });

        int owl = chronoMap.getOrDefault(Chronotype.OWL, 0);
        int pigeon = chronoMap.getOrDefault(Chronotype.PIGEON, 0);
        int lark = chronoMap.getOrDefault(Chronotype.LARK, 0);

        Chronotype res = Chronotype.PIGEON;
        if (owl > lark && owl > pigeon) {
            res = Chronotype.OWL;
        } else if (lark > owl && lark > pigeon) {
            res = Chronotype.LARK;
        }

        return new SleepAnalysisResult<>(res, "Хронотип");
    }
}