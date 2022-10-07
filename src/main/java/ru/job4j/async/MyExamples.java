package ru.job4j.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class MyExamples {

    public static void ex1() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                System.out.println("Ex1 Я буду работать в отдельном потоке, а не в главном.");
            }
        });
        future.get();

    }
    public static void ex2() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("Ex2 Я буду работать в отдельном потоке, а не в главном.");
        });
        future.get();
    }

    public static void ex3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new IllegalStateException(e);
                }
                return "Ex3 Результат асинхронной задачи";
            }
        });

        String result = future.get();
        System.out.println(result);
    }

    public static void ex4() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Ex4 Результат асинхронной задачи";
        });

        String result = future.get();
        System.out.println(result);
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 5; i++) {
            System.out.println("Основной поток!");
        }
        MyExamples.ex1();
        MyExamples.ex2();
        MyExamples.ex3();
        MyExamples.ex4();
    }
}
