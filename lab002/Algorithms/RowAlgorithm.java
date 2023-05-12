package lab2.Algorithms;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lab2.Interfaces.IAlgorithm;
import lab2.Models.Matrix;
import lab2.Models.Result;

public final class RowAlgorithm implements IAlgorithm {

    private final Matrix firstMatrix;
    private final Matrix secondMatrix;

    public RowAlgorithm(Matrix firstMatrix, Matrix secondMatrix) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
    }

    @Override
    public Result solve(int numberOfThreads) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Result solve() {
        var startTime = System.currentTimeMillis();
        var rowsCount = this.firstMatrix.getRows();
        var columnsCount = this.secondMatrix.getColumns();
        var resultMatrix = new int[rowsCount][columnsCount];

        for (var i = 0; i < rowsCount; i++) {
            for (var j = 0; j < columnsCount; j++) {
                for (var k = 0; k < rowsCount; k++) {
                    resultMatrix[i][j] += this.firstMatrix.getMatrix()[i][k] * this.secondMatrix.getMatrix()[k][j];
                }
            }
        }

        var endTime = System.currentTimeMillis();

        return new Result(new Matrix(resultMatrix), endTime - startTime);
    }
}
