package com.gmail.velikiydan.task_1;

import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class TextStatisticalAnalysis extends RecursiveTask<Map<String, Integer>> {
    private final List<String> lines;
    private static final int LINES_LIMIT = 100;

    private TextStatisticalAnalysis(List<String> lines) {
        this.lines = lines;
    }


    public static Map<String, Integer> wordsCount(ArrayList<String> lines) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        return pool.submit(new TextStatisticalAnalysis(lines)).join();
    }

    @Override
    protected Map<String, Integer> compute() {
        Map<String, Integer> wordsLength = new HashMap<>();

        if (lines.size() > LINES_LIMIT) {
            return ForkJoinTask.invokeAll(createSubTasks())
                    .stream()
                    .map(ForkJoinTask::join)
                    .collect(Collector.of(() -> wordsLength,
                            (result, wordsSizeMap) -> {
                                for (Map.Entry<String, Integer> entry : wordsSizeMap.entrySet()) {
                                    String word = entry.getKey();
                                    Integer size = entry.getValue();

                                    result.putIfAbsent(word, size);
                                }
                            },
                            (result1, result2) -> {
                                for (Map.Entry<String, Integer> entry : result2.entrySet()) {
                                    Integer count = result1.get(entry.getKey());
                                    count = count == null ? 0 : count;
                                    result1.put(entry.getKey(), count + entry.getValue());
                                }

                                return result1;
                            }));
        }

        return getWordsLength(lines);
    }

    private Collection<TextStatisticalAnalysis> createSubTasks() {
        ArrayList<TextStatisticalAnalysis> chunks = new ArrayList<>();

        for (int i = 0; i < lines.size(); i += LINES_LIMIT) {
            List<String> chunk = lines.subList(i, Math.min(i + LINES_LIMIT, lines.size()));
            chunks.add(new TextStatisticalAnalysis(chunk));
        }

        return chunks;
    }

    private Map<String, Integer> getWordsLength(List<String> lines) {
        Map<String, Integer> wordsLength = new HashMap<>();

        for (String line : lines) {
            String[] words = line.split("\\W+");

            for (String word : words) {
                if (word.length() > 0) {
                    wordsLength.putIfAbsent(word, word.length());
                }
            }
        }

        return wordsLength;
    }

    public static int getExpectedValue(Map<String, Integer> map) {
        ArrayList<Integer> values = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            values.add(entry.getValue());
        }

        Map<Integer, Long> valuesCount = values.stream()
                .collect(groupingBy(Function.identity(), Collectors.counting()));

        AtomicInteger res = new AtomicInteger();
        valuesCount.forEach((k, v) -> res.addAndGet(k * v.intValue() / values.size()));

        return res.get();
    }

    public static double getDispersion(Map<String, Integer> map) {
        ArrayList<Integer> values = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            values.add(entry.getValue());
        }

        double average = values.stream()
                .mapToInt(v -> v)
                .average()
                .orElse(0.0);

        Map<Integer, Long> valuesCount = values.stream()
                .collect(groupingBy(Function.identity(), Collectors.counting()));

        final double[] res = {0};
        valuesCount.forEach((k, v) -> res[0] += (Math.pow(k - average, 2) * v / values.size()));

        return res[0];
    }
}