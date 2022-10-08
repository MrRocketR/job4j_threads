package ru.job4j.async;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutionException;


public class SumsTest {

    @Test
    public void whenLinear() {
        int[][] matrix = new int[][]{{1, 2, 3 }, {5, 6, 7}, {9, 10, 11}};
       Sums[] sums = Sums.sum(matrix);
        Sums[] result = new Sums[]{new Sums(6, 15),
                new Sums(18, 18), new Sums(30, 21)};
        Assert.assertArrayEquals(result, sums);
    }
    @Test
    public void whenAsync() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][]{{1, 2, 3}, {5, 6, 7}, {9, 10, 11}};
       Sums[] sums = Sums.asyncSum(matrix);
       Sums[] result = new Sums[]{new Sums(6, 15),
                new Sums(18, 18), new Sums(30, 21)};
        Assert.assertArrayEquals(result, sums);
    }

}