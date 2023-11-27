package com.epam.multithreading.task2;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();

        Producer producer = new Producer(list);
        Consumer consumer1 = new Consumer(list);
        AdvancedConsumer consumer2 = new AdvancedConsumer(list);

        long start = System.currentTimeMillis();
        producer.start();
        consumer1.start();
        consumer2.start();

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long time = System.currentTimeMillis() - start;
        producer.interrupt();
        consumer1.interrupt();
        consumer2.interrupt();

        System.out.println("\n\nProduced " + list.size() + " elements");
        System.out.println("It took " + time + " ms");
    }
}
