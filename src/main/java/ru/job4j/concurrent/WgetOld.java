package ru.job4j.concurrent;

public class WgetOld {
    public static void main(String[] args) {
        Thread thread = new Thread(
                () -> {
                    try {
                        System.out.println("Start loading...");
                        for (int i = 0; i <= 100; i++) {
                            Thread.sleep(1000);
                            System.out.print("\rLoading : " + i + "%");
                        }
                        System.out.println("\nLoaded!");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        thread.start();
    }
}
