package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> distinctFiles = new HashMap<>();

    public void printDuplicates() {
        distinctFiles.keySet().stream()
                .filter(e -> distinctFiles.get(e).size() > 1)
                .forEach(e -> {
                    System.out.println(e.toString());
                    distinctFiles.get(e).stream().forEach(
                        x -> System.out.println("     " + x.toAbsolutePath())
                    );
                });
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (!attrs.isDirectory()) {
            FileProperty prop = new FileProperty(attrs.size(), file.getFileName().toString());
            if (distinctFiles.containsKey(prop)) {
                distinctFiles.get(prop).add(file);
            } else {
                List<Path> lst = new ArrayList<>();
                lst.add(file);
                distinctFiles.put(prop, lst);
            }
        }
        return super.visitFile(file, attrs);
    }

    public static void main(String[] args) throws IOException {
        DuplicatesVisitor duplicatesVisitor = new DuplicatesVisitor();
        Files.walkFileTree(Paths.get("C:\\test"), duplicatesVisitor);
        duplicatesVisitor.printDuplicates();
    }
}