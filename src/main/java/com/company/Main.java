package com.company;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the app. Write the number of csv files.");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String[] fileNames = new String[num];
        System.out.println("Write csv file names: (Names must not contain any spaces)");
        for (int i = 0; i < num; i++) {
            System.out.println("File " + i + ":");
            fileNames[i] = sc.next();
        }
        Storage storage = new Storage();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (String s : fileNames) {
            executorService.execute(new CSVInputReader(s, storage));
        }
        executorService.execute(new CSVWriter(storage));
    }
}
