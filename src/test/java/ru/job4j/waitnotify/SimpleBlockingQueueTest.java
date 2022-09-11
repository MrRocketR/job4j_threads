package ru.job4j.waitnotify;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {


    @Test
    public void threadTest() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue(10);
        Thread producer = new Thread(() -> {
            for (int i = 0; i <= 1000; i++) {
                simpleBlockingQueue.offer(i);
                i++;
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 0; i <= 1000; i++) {
                simpleBlockingQueue.poll();
                i++;
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }


}