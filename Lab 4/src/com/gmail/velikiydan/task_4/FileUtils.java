package com.gmail.velikiydan.task_4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileUtils {
    public static ArrayList<String> readTextFromFileByLines(String path) throws IOException {
        ArrayList<String> textLines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            stream.forEach(textLines::add);
        }
        return textLines;
    }

}
