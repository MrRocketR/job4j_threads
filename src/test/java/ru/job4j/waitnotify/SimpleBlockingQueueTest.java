package ru.job4j.waitnotify;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue(3);
    Thread producer = new Thread(() -> {
        simpleBlockingQueue.offer(1);
        simpleBlockingQueue.offer(2);
        simpleBlockingQueue.offer(3);
    });
    Thread consumer = new Thread(() -> {
        simpleBlockingQueue.poll();
        simpleBlockingQueue.poll();
        simpleBlockingQueue.poll();
    });
    @Ignore
    @Test
    public void threadTest() throws InterruptedException {
        producer.start();
        consumer.start();
        producer.join(1000);
        consumer.join(1000);
    }


}