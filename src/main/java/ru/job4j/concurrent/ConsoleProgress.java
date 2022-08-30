package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        char[] process = {'\\', '|', '/'};
        int counter = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\r load: " + process[counter]);
                Thread.sleep(100);
                counter = counter == process.length - 1 ? 0 : counter + 1;


            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
