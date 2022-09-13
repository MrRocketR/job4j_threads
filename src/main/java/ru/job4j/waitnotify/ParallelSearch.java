package ru.job4j.waitnotify;

public class ParallelSearch {
    public static void main(String[] args)  {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            queue.poll();
                        } catch (InterruptedException e) {
                            Thread.currentThread().isInterrupted();
                        }
                    }
                }
        );
        Thread producer = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    consumer.interrupt();
                }
        );

        try {
            consumer.start();
            producer.start();
            producer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
