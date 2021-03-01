package com.gmail.velikiydan.task_4;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String fileName = "/media/dan/linuxMedia/Projects/KPI/PRO_8_sem/Lab 4/src/com/gmail/velikiydan/task_4/files";

        List<String> filesNames = new ArrayList<>();
        FileSearchTask.showFiles(new File(fileName).listFiles(), filesNames);

        for(String f : filesNames) {
            System.out.println(f);
        }
        System.out.println();

        System.out.println(filesNames.size());
    }
}
