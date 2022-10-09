package ru.job4j.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    private static Sums linearSum(int[][] matrix, int row) {
        int rows = 0;
        int cols = 0;
        for (int i = 0; i < matrix.length; i++) {
            rows = rows + matrix[row][i];
            cols = cols + matrix[i][row];
        }
        return new Sums(rows, cols);
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] rsl = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rsl[i] = linearSum(matrix, i);
        }
        return rsl;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] rsl = new Sums[matrix.length];
        List<CompletableFuture> futures = new ArrayList<>();
        for (int row = 0; row < matrix.length; row++) {
            futures.add(getSum(matrix, row));
        }
        for (int i = 0; i < futures.size(); i++) {
            rsl[i] = (Sums) futures.get(i).get();
        }
        return rsl;
    }

    private static CompletableFuture<Sums> getSum(int[][] matrix, int row) {
        return CompletableFuture.supplyAsync(() -> linearSum(matrix, row));
    }


}
