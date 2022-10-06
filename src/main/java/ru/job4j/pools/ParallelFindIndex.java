package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFindIndex<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int start;
    private final int end;
    private final T required;
    private final int threshold = 10;

    public ParallelFindIndex(T[] array, int start, int end, T required) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.required = required;
    }

    private Integer findLinear() {
        for (int i = start; i < end; i++) {
            if (array[i].equals(required)) {
                return i;
            }
        }
        return -1;
    }

    public static <T> int findIndex(T[] array, T required) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelFindIndex<>(array, 0, array.length - 1, required));
    }


    @Override
    protected Integer compute() {
        if (end - start <= threshold) {
            return findLinear();
        }
        int mid = (end + start) / 2;
        ParallelFindIndex<T> first = new ParallelFindIndex<>(array, start, mid, required);
        ParallelFindIndex<T> second = new ParallelFindIndex<>(array, mid + 1, end, required);
        first.fork();
        second.fork();
        return Math.max(first.join(), second.join());
    }
}

