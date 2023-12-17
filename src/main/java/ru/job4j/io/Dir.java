package ru.job4j.io;

import java.io.File;
import java.util.Arrays;

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        if (args.length == 1) {
            throw new IllegalArgumentException("Extension is null");
        }
        File file = new File(args[0]);
        String extension = args[1];

        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        Arrays.stream(file.listFiles()).filter(x -> x.getName().endsWith(extension)).forEach(x -> {
            System.out.printf("File name = %s ; File size = %d \r\n", x.getName(), x.length());
        });
    }
}