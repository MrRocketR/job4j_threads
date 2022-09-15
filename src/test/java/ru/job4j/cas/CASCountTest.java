package ru.job4j.cas;

import org.junit.Assert;
import org.junit.Test;


public class CASCountTest {

    @Test
    public void whenWork() throws InterruptedException {
        CASCount count = new CASCount(10);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                count.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 6; i++) {
                count.increment();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        Assert.assertEquals(22, count.get());
    }

}