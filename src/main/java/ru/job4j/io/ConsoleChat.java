package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> phrases = readPhrases();
        List<String> chatLog = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean needStopChat = false;
        System.out.println("Insert any phrase: ");
        String in = scanner.nextLine();
        chatLog.add(in);
        while (!OUT.equals(in)) {
            if (STOP.equals(in)) {
                needStopChat = true;
            } else if (CONTINUE.equals(in)) {
                needStopChat = false;
            }
            if (!needStopChat) {
                String answer = phrases.get(new Random().nextInt(phrases.size()));
                System.out.println(answer);
                chatLog.add(answer);
            }
            in = scanner.nextLine();
            chatLog.add(in);
        }
        saveLog(chatLog);
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(this.botAnswers))) {
            result  = read.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter output = new PrintWriter(this.path)) {
            log.forEach(output::println);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chatLog.txt", "./data/phrases.txt");
        cc.run();
    }
}
