package com.epam.multithreading.task3;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int numberOfTopics = 2;
        MessageBus messageBus = new MessageBus(numberOfTopics);
        Producer[] producers = new Producer[numberOfTopics];
        Consumer[] consumers = new Consumer[numberOfTopics];

        for (int i = 0; i < numberOfTopics; i++) {
            producers[i] = new Producer(messageBus, i);
            consumers[i] = new Consumer(messageBus, i);
            producers[i].start();
            consumers[i].start();
        }

        try {
            System.in.read();
            for (int i = 0; i < numberOfTopics; i++) {
                producers[i].interrupt();
                consumers[i].interrupt();
            }
        } catch (IOException ignored) {
        }
    }
}
