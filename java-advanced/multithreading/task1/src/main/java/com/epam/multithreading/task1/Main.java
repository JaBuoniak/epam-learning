package com.epam.multithreading.task1;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        int productionLimit = 100000;
        int consumingInterval = 1;
        Map<Integer, Integer> map = new ThreadSafeMap<Integer, Integer>();
        Producer producer = new Producer(map, productionLimit);
        Consumer consumer = new Consumer(map, consumingInterval);

        long start = System.currentTimeMillis();
        producer.start();
        consumer.start();

        while (producer.isAlive() && consumer.isAlive());

        long time = System.currentTimeMillis() - start;
        producer.interrupt();
        consumer.interrupt();
        System.out.println("\n\nProduced " + map.size() + "/" + productionLimit + " elements before exception using " + map.getClass().getName());
        System.out.println("It took " + time + " ms");
        System.out.println("Consuming interval [ms]: " + consumingInterval);

    }
}
