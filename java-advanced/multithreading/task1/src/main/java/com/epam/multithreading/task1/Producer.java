package com.epam.multithreading.task1;

import java.util.Map;
import java.util.Random;

public class Producer extends Thread {
    private final Map<Integer, Integer> map;
    private final int limit;
    private final Random random = new Random();

    public Producer(Map<Integer, Integer> map, int limit) {
        this.map = map;
        this.limit = limit;
    }

    @Override
    public void run() {
        int counter = 0;
        while (counter < limit) {
            int value = random.nextInt() % 100;
            System.out.println("put (" + counter + ", " + value + ")");
            map.put(counter++, value);

            if (this.isInterrupted()) {
                return;
            }
        }
    }
}
