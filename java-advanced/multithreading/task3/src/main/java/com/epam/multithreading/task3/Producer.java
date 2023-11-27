package com.epam.multithreading.task3;

import java.util.Random;

public class Producer extends Thread {
    private final static String[] MESSAGE = {
            "0 Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
            "1 Praesent eu nulla posuere sapien mattis facilisis non sit amet nibh.",
            "2 Vivamus ullamcorper leo a facilisis gravida.",
            "3 In lobortis magna at orci blandit tristique.",
            "4 Nunc sit amet orci scelerisque, vulputate augue consectetur, vestibulum nibh.",
            "5 Aenean a orci dignissim, lobortis tortor nec, venenatis enim.",
            "6 Cras fermentum lorem in sem lobortis malesuada.",
            "7 Mauris efficitur neque sit amet ligula dignissim varius.",
            "8 Donec pharetra orci ut auctor viverra.",
            "9 Donec cursus sapien ut tempus feugiat.",
            "10 Curabitur finibus risus sit amet porttitor sollicitudin.",
            "11 Donec vel urna sed elit semper congue.",
            "12 Vivamus malesuada libero nec sollicitudin aliquet.",
            "13 Phasellus condimentum elit et ante finibus, vel varius augue vulputate.",
            "14 Etiam at nisi mattis orci dignissim porttitor.",
            "15 Morbi venenatis sapien a nunc varius, vitae porttitor arcu varius.",
            "16 Integer iaculis mauris ac quam imperdiet viverra."
    };
    private final Random random = new Random();
    private final MessageBus bus;
    private final int topicNumber;

    public Producer(MessageBus bus, int topicNumber) {
        this.bus = bus;
        this.topicNumber = topicNumber;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                return;
            }

            int messageNumber = random.nextInt(16);
            System.out.println("T" + topicNumber + ": Publish " + messageNumber);
            bus.publish(MESSAGE[messageNumber], topicNumber);

            if (isInterrupted()) return;
        }
    }
}
