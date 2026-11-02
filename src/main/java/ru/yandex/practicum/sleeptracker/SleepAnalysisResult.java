package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult<T> {
    private final T result;
    private final String description;

    public SleepAnalysisResult(T result, String description) {
        this.result = result;
        this.description = description;
    }

    public T getResult() {
        return result;
    }

    @Override
    public String toString() {
        return description + ": " +  result;
    }
}