package ru.job4j.pools;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.Assert.*;

public class ParallelFindIndexTest {

    @Ignore
    @Test
    public void whenFound() {
        Integer[] array = new Integer[] {1, 11, 45, 11, 65, 77, 88,
                22, 54, 100, 145, 123, 77,
                88, 222, 500, 700, 666, 777, 888};
        Integer req = 500;
        ParallelFindIndex parallelFindIndex = new ParallelFindIndex(array, 0, 21, req);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Assert.assertEquals(forkJoinPool.invoke(parallelFindIndex), req);
    }

}