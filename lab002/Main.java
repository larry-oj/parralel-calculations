package lab2;

import lab2.Algorithms.FoxAlgorithm;
import lab2.Algorithms.RowAlgorithm;
import lab2.Algorithms.StripedAlgorithm;
import lab2.Models.Matrix;

public class Main {
    public static void main(String[] args) {
        var firstMatrix = new Matrix(500, 500, 1);
        var secondMatrix = new Matrix(500, 500, 10);

//        var firstMatrix = new Matrix(1500, 1500, 1);
//        var secondMatrix = new Matrix(1500, 1500, 10);

//        var firstMatrix = new Matrix(2500, 2500, 1);
//        var secondMatrix = new Matrix(2500, 2500, 10);

        var processors = 4; // 8

        var rowAlgorithm = new RowAlgorithm(firstMatrix, secondMatrix);
        var rowResult = rowAlgorithm.solve();
        System.out.printf("Row: %s%n", rowResult);

        var stripedAlgorithm = new StripedAlgorithm(firstMatrix, secondMatrix);
        var stripedResult = stripedAlgorithm.solve(processors);
        System.out.printf("Striped: %s%n", stripedResult);

        var foxAlgorithm = new FoxAlgorithm(firstMatrix, secondMatrix);
        var foxResult = foxAlgorithm.solve(processors);
        System.out.printf("Fox: %s%n", foxResult);
    }
}
