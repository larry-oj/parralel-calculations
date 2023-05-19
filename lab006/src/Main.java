import Models.Matrix;

public class Main {
    public static void main(String[] args) {
        final int MATRIX_SIZE = 1000;

        var firstMatrix = new Matrix(MATRIX_SIZE, MATRIX_SIZE, 1);
        var secondMatrix = new Matrix(MATRIX_SIZE, MATRIX_SIZE, 10);

        if (true) {
            var blockingResult = new BlockingMultiplier(firstMatrix, secondMatrix, args).multiply();

            if (blockingResult == null) return;

            System.out.println("Blocking - " + blockingResult.toString());
        }
        else {
            var result = new NonBlockingMultiplier(firstMatrix, secondMatrix, args).multiply();

            if (result == null) return;

            System.out.println("Non-Blocking - " + result.toString());
        }
    }
}