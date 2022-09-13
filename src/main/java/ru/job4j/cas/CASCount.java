package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count;

    public CASCount(int n) {
        count = new AtomicReference<>(n);
    }


    public void increment() {
        int expected;
        int newValue;
        do {
            expected = count.get();
            newValue = expected + 1;
        } while (!count.compareAndSet(expected, newValue));
    }

    public int get() {
        return count.get();
    }

}
