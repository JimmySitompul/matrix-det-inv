import java.util.Scanner;

public class MatrixOperations {
    public static double determinant(double[][] matrix) {
        int n = matrix.length;

        if (n != matrix[0].length) {
            throw new IllegalArgumentException("Matrix must be square.");
        }

        if (n == 1) {
            return matrix[0][0];
        }

        double det = 0;
        int sign = 1;

        for (int i = 0; i < n; i++) {
            double[][] subMatrix = new double[n - 1][n - 1];
            getCofactor(matrix, subMatrix, 0, i);
            det += sign * matrix[0][i] * determinant(subMatrix);
            sign = -sign;
        }

        return det;
    }

    public static void getCofactor(double[][] matrix, double[][] subMatrix, int p, int q) {
        int n = matrix.length;
        int i = 0;
        int j = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (row != p && col != q) {
                    subMatrix[i][j++] = matrix[row][col];
                    if (j == n - 1) {
                        j = 0;
                        i++;
                    }
                }
            }
        }
    }

    public static double[][] inverseMatrix(double[][] matrix) {
        int n = matrix.length;

        double determinant = determinant(matrix);

        if (determinant == 0) {
            throw new IllegalArgumentException("Matrix is not invertible.");
        }

        double[][] inverse = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double[][] subMatrix = new double[n - 1][n - 1];
                getCofactor(matrix, subMatrix, i, j);
                inverse[j][i] = Math.pow(-1, i + j) * determinant(subMatrix) / determinant;
            }
        }

        return inverse;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows in the matrix: ");
        int rows = scanner.nextInt();

        System.out.print("Enter the number of columns in the matrix: ");
        int columns = scanner.nextInt();

        double[][] matrix = new double[rows][columns];

        System.out.println("Enter the matrix elements row by row:");

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        scanner.close();

        double det = determinant(matrix);
        System.out.println("Determinant: " + det);

        if (det != 0) {
            double[][] inverse = inverseMatrix(matrix);
            System.out.println("Inverse Matrix:");

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    System.out.print(inverse[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
