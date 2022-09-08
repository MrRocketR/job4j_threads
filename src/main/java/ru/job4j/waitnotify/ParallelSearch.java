package ru.job4j.waitnotify;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(5);
        Thread producer  = new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

        );
        producer.start();
        final Thread consumer = new Thread(
                () -> {
                    while (producer.getState() == Thread.State.RUNNABLE) {
                        System.out.println(queue.poll());
                    }
                }
        );
        consumer.start();
    }
}
