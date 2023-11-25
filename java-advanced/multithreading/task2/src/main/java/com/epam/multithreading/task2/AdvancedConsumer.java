package com.epam.multithreading.task2;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.concurrent.locks.ReentrantLock;

public class AdvancedConsumer extends Thread {

    private final Collection<Integer> collection;

    public AdvancedConsumer(Collection<Integer> collection) {
        this.collection = collection;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (collection) {
                    double sum = collection.stream().mapToDouble(Integer::doubleValue).map(a -> a * a).sum();
                    System.out.println("square root of sum of squares of " + collection.size() + " elements: " + Math.sqrt(sum));
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
