package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        String extension = args[1];
        search(start, p -> p.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    public static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("There should be 2 arguments");
        }
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is not specified");
        }
        if (args.length == 1) {
            throw new IllegalArgumentException("Extension is not specified");
        }
        Path path = Paths.get(args[0]);
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Invalid path");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Invalid extension");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}