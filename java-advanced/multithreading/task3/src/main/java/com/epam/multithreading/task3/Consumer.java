package com.epam.multithreading.task3;

import java.util.NoSuchElementException;

public class Consumer extends Thread {

    private final MessageBus bus;
    private int topicNumber;

    public Consumer(MessageBus bus, int topicNumber) {
        this.bus = bus;
        this.topicNumber = topicNumber;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                return;
            }

            try {
                System.out.println("T" + topicNumber + ": " + bus.read(topicNumber));
            } catch (NoSuchElementException e) {
                System.out.println("T" + topicNumber + ": Empty queue");
            }

            if (isInterrupted()) return;
        }
    }
}
