package com.company;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVInputReader extends Thread {
    private final String path;
    private Storage storage;

    public CSVInputReader(String path, Storage storage) {
        this.path = path;
        this.storage = storage;
    }

    @Override
    public void run() {
        synchronized (storage) {
            storage.increaseReaders();
        }
        String row;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(System.getProperty("user.dir") + "\\"+path));
            csvReader.readNext();
            String[] lineInArray;
            while ((lineInArray = csvReader.readNext()) != null) {
                System.out.println("File " + path + " is reading");
                Article article = new Article();
                article.setId(lineInArray[0]);
                article.setSourceId(lineInArray[1]);
                article.setSourceName(lineInArray[2]);
                article.setTitle(lineInArray[3]);
                article.setContent(lineInArray[4]);
                article.setPublishedAt(lineInArray[5]);
                System.out.println(article);
                synchronized (storage) {
                    storage.getStorage().add(article);
                }
            }
            csvReader.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } finally {
            synchronized (storage) {
                storage.decreaseReaders();
            }
        }
    }
}
