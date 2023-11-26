package com.epam.multithreading.task3;

import java.util.ArrayList;

public class MessageBus {
    ArrayList<Topic> topics;


    public MessageBus(int numberOfTopics) {
        if (numberOfTopics <= 0) throw new IllegalArgumentException("Number of topics should be positive.");
        topics = new ArrayList<>(numberOfTopics);
        for (int i = 0; i < numberOfTopics; i++) {
            topics.add(new Topic());
        }
    }

    public void publish(String message, int topicNumber) {
        topics.get(topicNumber).publish(message);
    }

    public String read(int topicNumber) {
        return topics.get(topicNumber).read();
    }
}
