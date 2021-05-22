package com.company.lab1;

import com.company.util.FilePaths;
import com.company.util.ParseFileMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class RunsMethod {

    public double[] runsMethod(double eps, int n){
        double[] A = new double[n+1];
        double[] B = new double[n+1];
        double[] C = new double[n+1];
        double[] F = new double[n+1];

        /*x1  = 0,
          xi-1 -eps*xi  + xi+1 =i/n,  i=2,3,…n-1,
          xn =1.
          n=21,41; eps=1;1,5;2.*/

        B[1] = 0;
        C[1] = 1;
        F[1] = 0;
        for(int i = 2; i <= n - 1; ++i){
            A[i] = 1;
            C[i] = eps;
            B[i] = 1;
            F[i] = -(double)i / n;
        }
        A[n] = 0;
        C[n] = 1;
        F[n] = 1;

        /*-----------------------------------Прямой ход---------------------------------------*/
        double[] y = new double[n+1];
        double[] alpha = new double[n+1];
        double[] beta = new double[n+1];
        alpha[1] = B[1]/C[1];
        beta[1]= F[1]/C[1];
        for (int i = 2; i < n; i++) {
            double tmp = C[i] - A[i] * alpha[i - 1];
            alpha[i] = B[i] / tmp;
            beta[i] = (F[i] + A[i]*beta[i-1])/tmp ;
        }
        /*-----------------------------------Прямой ход---------------------------------------*/

        /*-----------------------------------Обратный ход---------------------------------------*/
        y[n] = (F[n] + A[n]*beta[n-1])/(C[n] - A[n]*alpha[n-1]);
        for (int i = n - 1; i >= 1; i--) {
            y[i] = alpha[i] * y[i+1] + beta[i];
        }
        return y;
    }

    public static void main(String[] args) {
        RunsMethod matrix = new RunsMethod();
        System.out.println(Arrays.toString(matrix.runsMethod(1, 3)));
    }
}
