package com.epam.multithreading.task2;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread {

    private final Collection<Integer> collection;

    public Consumer(Collection<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (collection) {
                    long sum = collection.stream().mapToLong(Integer::longValue).sum();
                    System.out.println("sum of " + collection.size() + " elements: " + sum);
                }
            } catch (ConcurrentModificationException e) {
                e.printStackTrace();
                return;
            }

            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (this.isInterrupted()) return;
        }
    }
}
