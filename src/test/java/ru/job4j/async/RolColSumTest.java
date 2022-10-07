package ru.job4j.async;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class RolColSumTest {

    @Test
    public void whenLinear() {
        int[][] matrix = new int[][]{{1, 2, 3 }, {5, 6, 7}, {9, 10, 11}};
        RolColSum.Sums[] sums = RolColSum.Sums.sum(matrix);
        RolColSum.Sums[] result = new RolColSum.Sums[]{new RolColSum.Sums(6, 15),
                new RolColSum.Sums(18, 18), new RolColSum.Sums(30, 21)};
        Assert.assertArrayEquals(result, sums);
    }
    @Test
    public void whenAsync() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][]{{1, 2, 3}, {5, 6, 7}, {9, 10, 11}};
        RolColSum.Sums[] sums = RolColSum.Sums.asyncSum(matrix);
        RolColSum.Sums[] result = new RolColSum.Sums[]{new RolColSum.Sums(6, 15),
                new RolColSum.Sums(18, 18), new RolColSum.Sums(30, 21)};
        Assert.assertArrayEquals(result, sums);
    }

}