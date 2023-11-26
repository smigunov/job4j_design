package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Config {

    private final String path;
    private  Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            this.values = read.lines()
                    .filter(this::validate)
                    .filter(s -> s.matches("^.+=.+$"))
                    .map(s -> s.split("="))
                    .collect(Collectors.toMap(s -> s[0], s -> s[1]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validate(String s) {
        if (!(s.isEmpty() || s.charAt(0) == '#' || s.matches("^.+=.+$"))) {
            throw new IllegalArgumentException("Ошибка в строке: '" + s + "'");
        }
        return true;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("./data/corrupted_pairs.properties");
        config.load();
    }
}