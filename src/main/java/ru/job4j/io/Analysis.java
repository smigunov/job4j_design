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
            String[] result = read.lines()
                    .map(s -> s.split(" "))
                    .filter(s -> (startCodes.contains(s[0]) || endCodes.contains(s[0])))
                    .reduce(makeNewStringArray(), (accum, elem) -> {
                            if (startCodes.contains(elem[0]) && (accum[0] == "END" || accum[0].isEmpty())) {
                                accum[0] = "BEG";
                                accum[1] = accum[1] + elem[1] + ";";
                            } else if (endCodes.contains(elem[0]) && (accum[0] == "BEG")) {
                                accum[0] = "END";
                                accum[1] = accum[1] + " " + elem[1] + ";\r\n";
                            }
                            return accum;
                    });

            try (PrintWriter output = new PrintWriter(target)) {
                output.append(result[1]);
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}