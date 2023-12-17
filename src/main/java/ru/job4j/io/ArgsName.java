package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ArgsName {

    private Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format("This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        values = Arrays.stream(args)
                .filter(x -> validateStringFormat(x))
                .map(x -> x.substring(1).split("=", 2))
                .filter(x -> validateKeyAndValue(x))
                .collect(Collectors.toMap(
                        e -> e[0],
                        e -> e[1]
                ));
    }

    private boolean validateStringFormat(String str) {
        if (!str.contains("=")) {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not contain an equal sign", str));
        }
        if (str.charAt(0) != '-') {
            throw new IllegalArgumentException(String.format("Error: This argument '%s' does not start with a '-' character", str));
        }
        return true;
    }

    private boolean validateKeyAndValue(String[] s) {
        if (s[0].isEmpty()) {
            throw new IllegalArgumentException(String.format("Error: This argument '-%s=%s' does not contain a key", s[0], s[1]));
        }
        if (s[1].isEmpty()) {
            throw new IllegalArgumentException(String.format("Error: This argument '-%s=%s' does not contain a value", s[0], s[1]));
        }
        return true;
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}