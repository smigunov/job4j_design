package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    private final String file;
    List<String> filteredLst;

    public LogFilter(String file) {
        this.file = file;
        this.filteredLst = filter();
    }

    public List<String> filter() {
        List<String> result = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(this.file))) {
            result = in.lines().filter(s -> s.matches("^.*\\s404\\s\\d+$")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveTo(String out) {
        try (PrintWriter output = new PrintWriter(out)) {
            this.filteredLst.forEach(output::println);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}