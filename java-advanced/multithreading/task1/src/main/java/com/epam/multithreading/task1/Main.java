package com.epam.multithreading.task1;

import java.time.Clock;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        int producingInterval = 10;
        int consumingInterval = 27;
        Map<Integer, Integer> map = new HashMap<>();
        Producer producer = new Producer(map, producingInterval);
        Consumer consumer = new Consumer(map, consumingInterval);

        Clock clock = Clock.systemDefaultZone();
        long start = clock.millis();
        producer.start();
        consumer.start();

        while (consumer.isAlive());

        long time = clock.millis() - start;
        producer.interrupt();
        //consumer.interrupt();
        System.out.println("\n\nProduced " + map.size() + " elements before exception using " + map.getClass().getName());
        System.out.println("It took " + time + " ms");
        System.out.println("Producing interval: " + producingInterval);
        System.out.println("Consuming interval: " + consumingInterval);

    }
}
