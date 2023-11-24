package com.epam.multithreading.task1;

import java.util.Map;
import java.util.Random;

public class Producer extends Thread {
    private final Map<Integer, Integer> map;
    private int interval;
    private final Random random = new Random();

    public Producer(Map<Integer, Integer> map, int interval) {
        this.map = map;
        this.interval = interval;
    }

    @Override
    public void run() {
        int counter = 0;
        while(true) {
            try {
                int value = random.nextInt() % 100;
                System.out.println("put ("+ counter + ", " + value + ")");
                map.put(counter++, value);
                sleep(interval);
            } catch (InterruptedException e) {
                System.out.println("interrupted");
                return;
            }
            if (this.isInterrupted()) {
                return;
            }
        }
    }
}
