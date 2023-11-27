package com.epam.multithreading.task3;


import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class Topic {
    private final LinkedList<String> queue;
    private final ReentrantLock mutex;

    public Topic() {
        mutex = new ReentrantLock();
        queue = new LinkedList<>();
    }

    public void publish(String message) {
        mutex.lock();
        queue.add(message);
    }

    public String read() {
//        if (queue.isEmpty())
//            return "";
        return queue.removeFirst();
    }
}
