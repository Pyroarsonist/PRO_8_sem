package com.gmail.velikiydan.task_3;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String firstText = "/media/dan/linuxMedia/Projects/KPI/PRO_8_sem/Lab 4/src/com/gmail/velikiydan/task_3/text1.txt";
        String secondText = "/media/dan/linuxMedia/Projects/KPI/PRO_8_sem/Lab 4/src/com/gmail/velikiydan/task_3/text2.txt";

        List<String> commonWords = CommonWords.commonWords(FileUtils.readTextFromFileByLines(firstText),
                FileUtils.readTextFromFileByLines(secondText));
        System.out.println(commonWords.size());
    }
}
