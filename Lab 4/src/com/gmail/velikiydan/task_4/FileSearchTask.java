package com.gmail.velikiydan.task_4;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class FileSearchTask extends RecursiveAction {

    private final File[] files;
    private final List<String> resultFileNames;
    private static final List<String> THEME_WORDS = Arrays.asList("it", "126", "programming", "develop");


    private FileSearchTask(File[] files, List<String> result) {
        this.files = files;
        this.resultFileNames = result;
    }

    public static void showFiles(File[] files, List<String> result) {
        ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

        pool.submit(new FileSearchTask(files, result)).join();
    }

    @Override
    protected void compute() {
        for (File file : files) {
            if (file.isDirectory()) {
                ForkJoinTask.getPool()
                        .invoke(new FileSearchTask(file.listFiles(), resultFileNames));
            } else {
                checkFileTheme(file);
            }
        }
    }

    private void checkFileTheme(File file) {
        String fileContent = FileUtils.readFileToString(file);

        for (String word : THEME_WORDS) {
            if (fileContent.contains(word)) {
                this.resultFileNames.add(file.getPath());
            }
        }

    }
}