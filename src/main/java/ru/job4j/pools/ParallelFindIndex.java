package ru.job4j.pools;

import java.util.concurrent.RecursiveTask;

public class ParallelFindIndex extends RecursiveTask<Integer> {
    private final Integer[] array;
    private final Integer start;
    private final Integer end;
    private final Integer required;
    private final Integer threshold = 10;

    public ParallelFindIndex(Integer[] array, Integer start, Integer end, Integer required) {
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

    private static Integer find(Integer[] arr, Integer number) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].equals(number)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    protected Integer compute() {
        if (end - start <= threshold) {
            return findLinear();
        } else {
            int mid = (end + start) / 2;
            ParallelFindIndex first = new ParallelFindIndex(array, start, mid, required);
            ParallelFindIndex second = new ParallelFindIndex(array, mid + 1, array.length - 1, required);
            first.fork();
            second.fork();
            return Math.max(first.join(), second.join());
        }
    }
}

