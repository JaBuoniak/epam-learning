package com.epam.multithreading.task1;

import java.util.ConcurrentModificationException;
import java.util.Map;

public class Consumer extends Thread {

    private final Map<Integer, Integer> map;
    private int interval;

    public Consumer(Map<Integer, Integer> map, int interval) {
        this.map = map;
        this.interval = interval;
    }

    @Override
    public void run() {
        while (true) {
            try {
                long sum = map.values().stream().mapToLong(Integer::intValue).sum();
                System.out.println("sum of " + map.size() + " elements: " + sum);
            } catch (ConcurrentModificationException e) {
                return;
            }
            try {
                sleep(interval);
            } catch (InterruptedException e) {
                System.out.println("consuming interrupted");
                return;
            }
            if (this.isInterrupted()) return;
        }
    }
}
