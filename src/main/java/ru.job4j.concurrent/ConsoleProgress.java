package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    char[] process = {'\\', '|', '/'};
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r load: " + process[0]);
            System.out.print("\r load: " + process[1]);
            System.out.print("\r load: " + process[2]);
        }
    }
}
