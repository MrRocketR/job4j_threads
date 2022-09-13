package ru.job4j.pool;

import ru.job4j.waitnotify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    int size = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);


    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread t : threads) {
            t.interrupt();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        for (int i = 0; i < threadPool.size; i++) {
           Thread t  =  (new Thread(() -> {
               while (!Thread.currentThread().isInterrupted()) {
                   System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().getState());
               }
           }
           ));
            threadPool.threads.add(t);
            t.start();
            threadPool.work(t);
        }

        Thread.sleep(10000);
        threadPool.shutdown();

    }
}
