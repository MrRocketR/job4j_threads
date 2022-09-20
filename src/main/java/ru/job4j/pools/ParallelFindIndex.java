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

    private Integer findLinear() {
        for (int i = start; i < end; i++) {
             if (array[i].equals(required)) {
                return i;
            }
        }
        return -1;
    }

    private static Integer find(int[] arr, int number) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == number) {
                return i;
            }
        }
        return -1;
    }


    @Override
    protected  Integer compute() {
        if (array.length <= 10) {
            return findLinear();
        }
        ParallelFindIndex first = new ParallelFindIndex(array, start, array.length / 2, required);
        first.fork();
        ParallelFindIndex second = new ParallelFindIndex(array, array.length / 2, array.length - 1, required);
        second.fork();
        Integer n1 = first.join();
        Integer n2 = second.join();
        return Math.max(n1, n2);
    }

    public static void main(String[] args) {
       Integer[] array = new Integer[] {1, 11, 45, 11, 65, 77, 88,
                22, 54, 100, 145, 123, 77,
                88, 222, 500, 700, 666, 777, 888};
        ParallelFindIndex parallelFindIndex = new ParallelFindIndex(array, 0, 21, 700);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(parallelFindIndex));
    }
}

/*
так весь код тут для красоты, на ветки никакого деления нет, надо добавлять 2 таких блока.
 Нужен вызов join чтобы получить из вычислений результат.
 И потом полученный из двух веток надо вернуть максимум из двух
 */