package ru.job4j.waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    private int count = 0;

    public SimpleBlockingQueue(int count) {
        this.count = count;
    }

    public void offer(T value) {
        synchronized (queue) {
            queue.notify();
            while (queue.peek() == null) {
                queue.add(value);
                System.out.println("added " + value);
            }
            try {
                queue.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public T poll() {
        T r = null;
        synchronized (queue) {
            queue.notify();
            while (queue.peek() != null) {
                r =  queue.poll();
                System.out.println("Polled" + r);
            }
            try {
                queue.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
       return r;
    }
}
