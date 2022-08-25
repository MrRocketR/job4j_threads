package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        char[] process = {'\\', '|', '/'};
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\r load: " + process[0]);
                Thread.sleep(100);
                System.out.print("\r load: " + process[1]);
                Thread.sleep(100);
                System.out.print("\r load: " + process[2]);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }


        }
    }
}
