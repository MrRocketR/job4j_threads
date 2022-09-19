package ru.job4j.pools;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFindIndex  extends RecursiveTask<Integer> {

    private final Integer[] array;
    private final int start;
    private final int end;
    private final  Integer required;


    public ParallelFindIndex(Integer[] array,  int start, int end, Integer required) {
        this.array = array;
        this.start = start;
        this.end = end;
        this.required = required;
    }

    private Integer findLinear(Integer[] array,  Integer t) {
        for (int i = start; i < end; i++) {
             if (array[i].equals(t)) {
                return t;
            }
        }
        return null;
    }


    @Override
    protected  Integer compute() {
        if (array.length <= 10) {
            return findLinear(array, required);
        }
        ParallelFindIndex first = new ParallelFindIndex(array, start, end, required);
        first.fork();
        return first.findLinear(array, required);
    }

    public static void main(String[] args) {
       Integer[] array = new Integer[] {1, 11, 45, 11, 65, 77, 88,
                22, 54, 100, 145, 123, 77,
                88, 222, 500, 700, 666, 777, 888};
        ParallelFindIndex parallelFindIndex = new ParallelFindIndex(array, 0, 21, 500);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(parallelFindIndex));
    }
}
