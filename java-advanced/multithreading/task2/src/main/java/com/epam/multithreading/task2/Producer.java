package com.epam.multithreading.task2;

import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Producer extends Thread {
    private final Random random = new Random();
    private final Collection<Integer> collection;

    public Producer(Collection<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public void run() {
        while (true) {
            int value = random.nextInt();
            synchronized (collection) {
                collection.add(value);
            }
            if (this.isInterrupted()) {
                return;
            }
        }
    }
}
