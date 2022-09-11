package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount(int n) {
        count.set(n);
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

    public static void main(String[] args) {
        CASCount count = new CASCount(0);
        count.increment();
        count.increment();
        System.out.println(count.get());
    }
}
