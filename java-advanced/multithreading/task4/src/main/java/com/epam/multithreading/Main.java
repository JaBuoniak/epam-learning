package com.epam.multithreading;

public class Main {
    public static void main(String[] args) {

        BlockingObjectPool pool = new BlockingObjectPool(4);
        Producer producer = new Producer(pool, 10);
        Consumer consumer = new Consumer(pool);

        producer.start();
        consumer.start();
    }
}
