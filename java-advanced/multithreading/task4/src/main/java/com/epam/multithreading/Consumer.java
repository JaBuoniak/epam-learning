package com.epam.multithreading;

public class Consumer extends Thread {

    private BlockingObjectPool pool;

    public Consumer(BlockingObjectPool pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        Integer value = Integer.MIN_VALUE;
        do {
            try {
                value = (Integer) pool.get();

                System.out.println("Consumed " + value);

                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (value != Integer.MIN_VALUE);
    }
}
