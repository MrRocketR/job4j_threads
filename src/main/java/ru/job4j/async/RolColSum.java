package ru.job4j.async;

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
                rows += matrix[row][i];
                cols += matrix[i][row];
            }
            return new Sums(rows, cols);
        }

        public static Sums[] sum(int[][] matrix) {
            Sums[] rsl = new Sums[matrix.length];
            for (int row = 0; row < matrix.length; row++) {
                rsl[row] = linearSum(matrix, row);
            }
            return rsl;
        }
    }

    public static Sums[] asyncSum(int[][] matrix) {
        return null;
    }

}
