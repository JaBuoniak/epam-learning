package com.epam.multithreading;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Pool that block when it has not any items or it full
 */
public class BlockingObjectPool {

    private final LinkedBlockingQueue<Object> queue;

    /**
     * Creates filled pool of passed size
     *
     * @param size of pool
     */
    public BlockingObjectPool(int size) {
        queue = new LinkedBlockingQueue<>(size);
    }

    /**
     * Gets object from pool or blocks if pool is empty
     *
     * @return object from pool
     */
    public Object get() throws InterruptedException {
        return queue.take();
    }

    /**
     * Puts object to pool or blocks if pool is full
     *
     * @param object to be taken back to pool
     */
    public void take(Object object) {
        queue.offer(object);
    }
}