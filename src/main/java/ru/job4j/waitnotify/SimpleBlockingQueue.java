package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    private final int count;

    public SimpleBlockingQueue(int n) {
        this.count = n;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (queue) {
                while (queue.size() == count) {
                    System.out.println("Queue is full!");
                    queue.wait();
                }
            queue.add(value);
            System.out.println("Added " + value);
            queue.notify();
        }
    }

    public T poll() throws InterruptedException {
        T r = null;
        synchronized (queue) {
                while (queue.size() == 0) {
                    System.out.println("Queue is empty!");
                    queue.wait();
                }

            r = queue.poll();
            System.out.println("Polled = " + r);
            queue.notify();
        }
        return r;
    }

    public boolean isEmpty() {
        synchronized (queue) {
            return queue.isEmpty();
        }
    }
}
