package ru.job4j.pools;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFindIndex<T> extends RecursiveTask<T> {

    private final T[] array;
    private final T required;

    public ParallelFindIndex(T[] array, T required) {
        this.array = array;
        this.required = required;
    }

    private T findLinear(T[] array, T t) {
        T[] arr = array;
        for (int i = 0; i < arr.length - 1; i++) {
             if (arr[i] == t) {
                return t;
            }
        }
        return null;
    }
    private T findWithPool(T[] array, int start, int finish, T t) {
        for (int i = start; i < finish; i++) {
            if (array[i] == t) {
                return t;
            }
        }
        return null;
    }


    @Override
    protected T compute() {
        if (array.length <= 10) {
            return findLinear(array, required);
        }
        ParallelFindIndex<T> left = new  ParallelFindIndex<>(Arrays.copyOfRange(array, 0, array.length / 2), required);
        ParallelFindIndex<T> right = new  ParallelFindIndex<>(Arrays.copyOfRange(array, array.length / 2, array.length), required);
        left.fork();
        right.fork();
        T findLeft = left.join();
        T findRight = right.join();
        return null;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] {1, 11, 45, 11, 65, 77, 88,
                22, 54, 100, 145, 123, 77,
                88, 222, 500, 700, 666, 777, 888};
        Integer required = 500;
        ParallelFindIndex parallelFindIndex = new ParallelFindIndex(array, required);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(parallelFindIndex);
    }
}
