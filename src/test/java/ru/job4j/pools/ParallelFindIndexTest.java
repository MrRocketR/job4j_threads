package ru.job4j.pools;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;

public class ParallelFindIndexTest {

    @Test
    public void whenFound() {
        Integer[] array = new Integer[]{1, 11, 45, 11, 65, 77, 88,
                22, 54, 100, 145, 123, 77,
                88, 222, 500, 700, 666, 777, 888, 1000, 565, 976, 100, 45};
        Integer req = 500;
        Integer expected = 15;
        Integer result = ParallelFindIndex.findIndex(array, req);
        Assert.assertEquals(expected, result);
    }

}