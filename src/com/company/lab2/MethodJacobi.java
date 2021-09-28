package com.company.lab2;

import com.company.util.FilePaths;
import com.company.util.MatrixInfo;
import com.company.util.ParseFileMatrix;

import java.util.Arrays;

import static java.lang.Math.abs;

public class MethodJacobi {

    private double[] methodJacobi(MatrixInfo matrixInfo){
        var A = matrixInfo.getA();
        var x = matrixInfo.getX();
        var b = matrixInfo.getB();
        var n = matrixInfo.getN();
        var eps = matrixInfo.getEps();

        double[] nextX = new double[n + 1];
        int k = 0;
        double norm = 0;
        do {
            /**
             * x[i] ^ 0  = b[i]/a[i][i]
             * x[i]^(k+1) = 1/a[i][i] * (b[i] - sum(j = 1, n; j != n; a[i][j] * x[j]^k)
             * i = 1,n; k = 0,inf
             * */
            for (int i = 1; i <= n; ++i) {
                double sum = 0;
                for (int j = 1; j <= n; ++j) {
                    if (i != j) {
                        sum = sum + A[i][j] * x[j];
                    }
                }
                nextX[i] = (b[i] - sum) / A[i][i];
            }

            norm = abs(nextX[1] - x[1]);
            for (int i = 1; i <= n; ++i) {
                if (abs(nextX[i] - x[i]) > norm) {
                    norm = abs(nextX[i] - x[i]);
                }
                x[i] = nextX[i];
            }
            k++;
        } while (norm > eps);

        System.out.println("Iteration count: " + k + " \nEps: " + eps);
        System.out.println(Arrays.toString(x));
        return x;
    }

    public static void main(String[] args) {
        MatrixInfo matrixInfo = ParseFileMatrix.parseMatrixInfo(FilePaths.pathToJacobi);
        System.out.println(matrixInfo);
        var x = new MethodJacobi().methodJacobi(matrixInfo);
        System.out.println("Ответ");
        for (int i = 1; i < x.length; ++i)
            System.out.println("x"+ i + "=" + x[i]);

    }

}