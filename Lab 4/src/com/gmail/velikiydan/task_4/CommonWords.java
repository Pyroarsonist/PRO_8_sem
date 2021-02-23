package com.gmail.velikiydan.task_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

public class CommonWords extends RecursiveTask<List<String>> {
    private final List<String> firstText;
    private final List<String> secondText;

    private static final int LINES_LIMIT = 200;

    private CommonWords(List<String> firstText, List<String> secondText) {
        this.firstText = firstText;
        this.secondText = secondText;
    }


    public static List<String> commonWords(List<String> firstText, List<String> secondText) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        return pool.submit(new CommonWords(firstText, secondText)).join();
    }

    @Override
    protected List<String> compute() {
        if (firstText.size() > LINES_LIMIT) {
            return ForkJoinTask.invokeAll(createSubTasks())
                    .stream()
                    .map(ForkJoinTask::join)
                    .flatMap(Collection::stream)
                    .distinct()
                    .collect(Collectors.toList());
        }

        return getCommonWords(firstText, secondText);
    }

    private Collection<CommonWords> createSubTasks() {
        List<CommonWords> chunks = new ArrayList<>();

        chunks.add(new CommonWords(firstText.subList(0, firstText.size() / 2),
                secondText.subList(0, secondText.size() / 2)));
        chunks.add(new CommonWords(firstText.subList(firstText.size() / 2, firstText.size()),
                secondText.subList(secondText.size() / 2, secondText.size())));

        return chunks;
    }

    private List<String> getCommonWords(List<String> firstText, List<String> secondText) {
        List<String> firstTextWords = new ArrayList<>();
        List<String> secondTextWords = new ArrayList<>();

        for (String s : firstText) {
            firstTextWords.addAll(Arrays.asList(s.split("\\W+")));
        }

        for (String s : secondText) {
            secondTextWords.addAll(Arrays.asList(s.split("\\W+")));
        }

        firstTextWords.retainAll(secondTextWords);

        return firstTextWords;
    }
}