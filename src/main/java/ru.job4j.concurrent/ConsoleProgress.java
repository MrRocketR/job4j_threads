package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        char[] process = {'\\', '|', '/'};
        int counter = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                if (counter < 3) {
                    System.out.print("\r load: " + process[counter]);
                    Thread.sleep(1000);
                    counter++;
                } else {
                    counter = 1;
                    System.out.print("\r load: " + process[0]);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }


        }
    }
}
