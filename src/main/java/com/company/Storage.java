package com.company;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private List<Article> storage;
    private Integer numOfReaders = 0;

    public synchronized void decreaseReaders() {
        numOfReaders--;
        notifyAll();
    }

    public synchronized void increaseReaders() {
        numOfReaders++;
        notifyAll();
    }

    public synchronized void blockWriter() throws InterruptedException {
        while (numOfReaders > 0) {
            wait();
        }
    }

    public Storage() {
        this.storage = new ArrayList<Article>();
    }

    public List<Article> getStorage() {
        return storage;
    }
}
