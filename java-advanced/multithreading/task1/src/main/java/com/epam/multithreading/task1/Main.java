package com.epam.multithreading.task1;

import java.time.Clock;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) {
        int productionLimit = 100000;
        int consumingInterval = 1;
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        Producer producer = new Producer(map, productionLimit);
        Consumer consumer = new Consumer(map, consumingInterval);

        Clock clock = Clock.systemDefaultZone();
        long start = clock.millis();
        producer.start();
        consumer.start();

        while (producer.isAlive() && consumer.isAlive());

        long time = clock.millis() - start;
        producer.interrupt();
        consumer.interrupt();
        System.out.println("\n\nProduced " + map.size() + "/" + productionLimit + " elements before exception using " + map.getClass().getName());
        System.out.println("It took " + time + " ms");
        System.out.println("Consuming interval [ms]: " + consumingInterval);

    }
}
