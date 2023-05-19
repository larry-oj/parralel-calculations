import Models.Matrix;

public class Main {
    public static void main(String[] args) {
        final int MATRIX_SIZE = 500;

        var firstMatrix = new Matrix(MATRIX_SIZE, MATRIX_SIZE, 1);
        var secondMatrix = new Matrix(MATRIX_SIZE, MATRIX_SIZE, 10);

        var result = new Multiplier(firstMatrix, secondMatrix, args).multiply();

        if (result == null) return;

        System.out.println(result);
    }
}