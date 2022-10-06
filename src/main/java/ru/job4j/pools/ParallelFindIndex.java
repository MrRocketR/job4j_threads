package ru.job4j.pools;

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

    private static Integer find(Integer[] arr, Integer req) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].equals(req)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    protected Integer compute() {
        if (end - start <= threshold) {
            return findLinear();
        }
        int mid = (end + start) / 2;
        ParallelFindIndex first = new ParallelFindIndex(array, start, mid, required);
        ParallelFindIndex second = new ParallelFindIndex(array, mid + 1, array.length - 1, required);
        first.fork();
        second.fork();
        return Math.max((Integer) first.join(), second.compute());
    }
}

