package ru.job4j.async;

import java.util.Objects;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

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

        @Override
        public String toString() {
            return "Sums{" +
                    "rowSum=" + rowSum +
                    ", colSum=" + colSum +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum &&
                    colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }

    public static Sums[] asyncSum(int[][] matrix) {
        return null;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[10][2];
        System.out.println(matrix.length);
    }
}
