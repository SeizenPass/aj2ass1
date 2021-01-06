package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CSVWriter extends Thread {
    private final Storage storage;
    public CSVWriter(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        try {
            synchronized (storage) {
                storage.blockWriter();
            }
                Map<String, Publisher> publisherMap = new HashMap<String, Publisher>();
                Map<String, List<String>> contentLengthMap = new HashMap<String, List<String>>();
                for (Article article: storage.getStorage()) {
                    if (publisherMap.get(article.getSourceId()) == null) {
                        publisherMap.put(article.getSourceId(),
                                new Publisher(article.getSourceName(), article.getSourceId(),
                                        article.getPublishedAt(), article.getPublishedAt(), 0));
                        contentLengthMap.put(article.getSourceId(), new LinkedList<String>());
                    }
                    else {
                        String fromSt = publisherMap.get(article.getSourceId()).getPublishedFrom();
                        fromSt=fromSt.replace("T", " ");
                        fromSt=fromSt.replace("Z", "");
                        Date from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fromSt);
                        String toSt = publisherMap.get(article.getSourceId()).getPublishedTo();
                        toSt=toSt.replace("T", " ");
                        toSt=toSt.replace("Z", "");
                        Date to = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(toSt);
                        String artTime = article.getPublishedAt();
                        artTime=artTime.replace("T", " ");
                        artTime=artTime.replace("Z", "");
                        Date artDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(artTime);
                        if (artDate.compareTo(from) < 0) {
                            publisherMap.get(article.getSourceId()).setPublishedFrom(article.getPublishedAt());
                        }
                        if (artDate.compareTo(to) > 0) {
                            publisherMap.get(article.getSourceId()).setPublishedTo(article.getPublishedAt());
                        }
                    }
                    contentLengthMap.get(article.getSourceId()).add(article.getContent());
                }
                for (String key:
                     contentLengthMap.keySet()) {
                    List<String> list = contentLengthMap.get(key);
                    long numberOfChars = 0;
                    for (String s:
                         list) {
                        long val = 0;
                        val += s.length();
                        if (s.contains("chars]")) {
                            String temp = "";
                            int index = s.lastIndexOf("[+");
                            for (int i = index+1; i < s.length(); i++) {
                                if (Character.isDigit(s.charAt(i))) temp += s.charAt(i);
                                else if (s.charAt(i) == ' ') break;
                            }
                            val += Integer.parseInt(temp);
                        }
                        numberOfChars += val;
                    }
                    long avg = numberOfChars / list.size();
                    publisherMap.get(key).setAvgContentLength(avg);
                }
                File myObj = new File("report.csv");
                if (myObj.createNewFile()) {
                    System.out.println("File created: " + myObj.getName());
                } else {
                    System.out.println("File already exists.");
                }
                FileWriter myWriter = new FileWriter("report.csv");
                for (String a:
                        publisherMap.keySet()) {
                    myWriter.write(publisherMap.get(a) + "\n");
                }
                myWriter.close();

        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
