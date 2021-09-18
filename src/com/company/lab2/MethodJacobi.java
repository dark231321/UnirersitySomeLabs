package com.company.lab2;

import com.company.util.FilePaths;
import com.company.util.MatrixInfo;
import com.company.util.ParseFileMatrix;

import java.util.Arrays;

import static java.lang.Math.abs;

public class MethodJacobi {

    private double[] methodJacobi(MatrixInfo matrixInfo, double eps){
        var A = matrixInfo.getA();
        var x = matrixInfo.getX();
        var b = matrixInfo.getB();
        var n = matrixInfo.getN();

        double[] NextX = new double[n + 1];
        int k = 0;
        double norm = 0;
        do {
            for (int i = 1; i <= n; ++i) {
                double sum = 0;
                for (int j = 1; j <= n; ++j) {
                    if (i != j) {
                        sum = sum + A[i][j] * x[j];
                    }
                }
                NextX[i] = (b[i] - sum) / A[i][i];
            }

            norm = abs(NextX[1] - x[1]);
            for (int i = 1; i <= n; ++i) {
                if (abs(NextX[i] - x[i]) > norm) {
                    norm = abs(NextX[i] - x[i]);
                }
                x[i] = NextX[i];
            }
            k++;
        } while (norm > eps);

        System.out.println("Iteration count: " + k + " \nEps: " + eps);

        for (int i = 1; i <= n; ++i){
            for (int j = 1; j <= n; ++j) {
                b[i] = b[i] - A[i][j] * x[j];
            }

            if (norm < abs(b[i])) {
                norm = abs(b[i]);
            }
        }
//        System.out.println("Residual: " + norm);
        System.out.println(Arrays.toString(x));
        return x;
    }

    public static void main(String[] args) {
        MatrixInfo matrixInfo = ParseFileMatrix.parseMatrixInfo(FilePaths.pathToJacobi);
        System.out.println(matrixInfo);
        var methodJacobi = new MethodJacobi();
        double eps = 0.001;
        var x = methodJacobi.methodJacobi(matrixInfo, eps);
        System.out.println("Ответ");
        for (int i = 1; i < x.length; ++i)
            System.out.println("x"+ i + "=" + x[i]);

    }

}