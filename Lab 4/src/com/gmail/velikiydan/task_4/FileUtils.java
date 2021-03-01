package com.gmail.velikiydan.task_4;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileUtils {
    public static String readFileToString(File file) {
        StringBuilder contentBuilder = new StringBuilder();

        try {
            InputStream is = new FileInputStream(file);

            Reader r = new InputStreamReader(is, StandardCharsets.UTF_8);
            int c;
            while ((c = r.read()) != -1) {
                contentBuilder.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return contentBuilder.toString();
    }

}
