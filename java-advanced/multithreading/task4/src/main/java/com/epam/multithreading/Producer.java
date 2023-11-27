package com.epam.multithreading;

import java.util.Random;

public class Producer extends Thread {

    private final Random random = new Random();
    private final BlockingObjectPool pool;
    private final int amount;

    public Producer(BlockingObjectPool pool, int amount) {
        this.pool = pool;
        this.amount = amount;
    }

    @Override
    public void run() {
        for (int i = 0; i < amount; i++) {

            pool.take(i);
            System.out.println("Produced " + i);

            try {
                sleep(random.nextInt(100));
            } catch (InterruptedException e) {
                return;
            }
        }

        pool.take(Integer.MIN_VALUE);
    }
}
