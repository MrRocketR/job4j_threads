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
                    queue.wait();
                }
            queue.add(value);
            queue.notify();
        }
    }

    public T poll() throws InterruptedException {
        T r = null;
        synchronized (queue) {
                while (queue.size() == 0) {
                    queue.wait();
                }

            r = queue.poll();
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
