package com.company.lab2;

import com.company.util.FilePaths;
import com.company.util.MatrixInfo;
import com.company.util.ParseFileMatrix;

import java.util.Arrays;

import static java.lang.Math.abs;

public class RelaxationMethod {

    private double[] relaxationMethod(MatrixInfo matrixInfo, double eps, double omega) {
        var A = matrixInfo.getA();
        var x = matrixInfo.getX();
        var b = matrixInfo.getB();
        var n = matrixInfo.getN();


        double[] NextX = new double[n + 1];
        int k = 0;
        double norm;
        do {
            norm=0;
            for (int i = 1; i <= n; ++i) {
                double sum = 0;
                for (int j = 2; j <= n; ++j) {
                    if (i != j) {
                        sum = sum + A[i][j] * x[j];
                    }
                }
                double Next = (1-omega)*x[i] + omega*(b[i] - sum) / A[i][i];

                if (abs(Next - x[i]) > norm) {
                    norm = abs(Next - x[i]);
                }
                x[i] = Next;
            }
            k++;
        } while (norm > eps);

        System.out.println("Iteration count: " + k + " \nEps: " + eps);
        norm=0;

        for (int i = 1; i <= n; ++i){
            for (int j = 1; j <= n; ++j) {
                b[i] = b[i] - A[i][j] * x[j];
            }
            if (norm < abs(b[i])) {
                norm = abs(b[i]);
            }
        }
        System.out.println("Residual= " + norm);
        return x;
    }

    public static void main(String[] args) {
        MatrixInfo matrixInfo = ParseFileMatrix.parseMatrixInfo(FilePaths.pathToMatrixFile);
        System.out.println(matrixInfo);
        var relaxationMethod = new RelaxationMethod();
        double eps = 0.00001;
        double omega = 0.9;


        var x= relaxationMethod.relaxationMethod(matrixInfo, eps, omega);

        System.out.println("Ответ");
        for (int i = 1; i < x.length; ++i)
            System.out.println("x"+ i + "=" + x[i]);

    }

}
