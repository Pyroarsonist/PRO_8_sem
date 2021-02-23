package com.gmail.velikiydan.task_1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = "/media/dan/linuxMedia/Projects/KPI/PRO_8_sem/Lab 4/src/com/gmail/velikiydan/task_1/text.txt";

        ArrayList<String> textLines = FileUtils.readTextFromFileByLines(fileName);

        //long startTime = System.nanoTime();
        Map<String, Integer> wordsLength = TextStatisticalAnalysis.wordsCount(textLines);
        //long endTime = System.nanoTime();

        //long duration = (endTime - startTime) / 1000000;
        //System.out.println(duration);

        System.out.println("Expected value: " + TextStatisticalAnalysis.getExpectedValue(wordsLength));
        System.out.println("Dispersion value: " + TextStatisticalAnalysis.getDispersion(wordsLength));
    }
}
