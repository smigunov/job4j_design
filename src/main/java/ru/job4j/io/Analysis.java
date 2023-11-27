package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class Analysis {
    private String[] makeNewStringArray() {
        String[] arr = new String[2];
        arr[0] = new String();
        arr[1] = new String();

        return arr;
    }

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            List<String> startCodes = List.of("400", "500");
            List<String> endCodes = List.of("200", "300");
            PrintWriter output = new PrintWriter(target);
            String line;
            boolean isStarted = false;
            while ((line = read.readLine()) != null) {
                String[] elm = line.split(" ");
                if (startCodes.contains(elm[0]) && !isStarted) {
                    output.append(elm[1]).append(";");
                    isStarted = true;
                } else if (endCodes.contains(elm[0]) && isStarted) {
                    output.append(elm[1]).append(";\r\n");
                    isStarted = false;
                }
            }
            output.flush();
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}