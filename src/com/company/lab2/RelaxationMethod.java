package com.company.lab2;

import com.company.util.FilePaths;
import com.company.util.MatrixInfo;
import com.company.util.ParseFileMatrix;

import static java.lang.Math.abs;

public class RelaxationMethod {

    private double[] relaxationMethod(MatrixInfo matrixInfo, double eps, double omega) {
        var A = matrixInfo.getA();
        var x = matrixInfo.getX();
        var b = matrixInfo.getB();
        var n = matrixInfo.getN();

        int k = 0;
        double[] nextX = new double[n + 1];
        double norm = 0;
        do {
            /**
             * x[i] ^ 0  = b[i]/a[i][i]
             * x[i]^(k+1) = omega /a[i][i] * ((b[i] - sum(j = 1, n; j = i - 1; a[i][j] * x[j] ^ k + 1) - sum(j = i + 1; j = n; a[i][j] * x[j] ^ k + 1)) + (1 - omega) * x[i] ^ k
             * */
            for (int i = 1; i <= n; ++i) {
                double sum1 = 0, sum2 = 0;
                for (var j = 1; j <= i-1; j++) {
                    sum1 += A[i][j] * nextX[j];
                }
                for (var j = i+1; j <= n; j++) {
                    sum2 += A[i][j] * x[j];
                }
                nextX[i] = omega*(b[i] - sum1 - sum2) / A[i][i] + (1-omega) * x[i] ;
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
        return x;
    }

    public static void main(String[] args) {
        MatrixInfo matrixInfo = ParseFileMatrix.parseMatrixInfo(FilePaths.pathToMatrixFile);
        System.out.println(matrixInfo);
        var relaxationMethod = new RelaxationMethod();

        double eps = 0.00001;
        /**
         * 0 < omega < 1 нижняя релаксация
         * 1 < omega < 2 верхняя релаксация
         * omega = 1 в точности метод Зейделя
         * */
        double omega = 0.5;


        var x= relaxationMethod.relaxationMethod(matrixInfo, eps, omega);

        System.out.println("Ответ");
        for (int i = 1; i < x.length; ++i)
            System.out.println("x"+ i + "=" + x[i]);

    }

}
