package com.company.lab1;

public class RunsMethod {

    public double[] runsMethod(double b, int n){
        double[] A = new double[n+1];
        double[] B = new double[n+1];
        double[] C = new double[n+1];
        double[] F = new double[n+1];

        B[1] = 0;
        C[1] = 1;
        F[1] = 0;
        for(int i = 2; i <= n - 1; ++i){
            A[i] = -1;
            C[i] = -b;
            B[i] = -1;
            F[i] = (double)i / n;
        }
        A[n] = 0;
        C[n] = 1;
        F[n] = 1;

        System.out.print("A = ");
        for (int i = 1; i <= n; i++) {
            System.out.print(A[i] + " ");
        }
        System.out.println();

        System.out.print("B = ");
        for (int i = 1; i <= n; i++) {
            System.out.print(B[i] + " ");
        }
        System.out.println();

        System.out.print("C = ");
        for (int i = 1; i <= n; i++) {
            System.out.print(C[i] + " ");
        }
        System.out.println();

        System.out.print("F = ");
        for (int i = 1; i <= n; i++) {
            System.out.print(F[i] + " ");
        }
        System.out.println();
        /*-----------------------------------Прямой ход---------------------------------------*/
        double[] x = new double[n+1];
        double[] alpha = new double[n+1];
        double[] beta = new double[n+1];
        alpha[1] = B[1] / C[1];
        beta[1] = F[1] / C[1];
        for (int i = 2; i <= n - 1; i++) {
            double tmp = (C[i] - A[i] * alpha[i - 1]);
            alpha[i] = B[i] / tmp;
            beta[i] = (F[i] + A[i] * beta[i - 1]) / tmp;
        }


        /*-----------------------------------Прямой ход---------------------------------------*/

        /*-----------------------------------Обратный ход---------------------------------------*/

        x[n] = (F[n] + A[n] * beta[n - 1]) / (C[n] - A[n] * alpha[n - 1]);
        for (int i = n - 1; i >= 1; i--) {
            x[i] = alpha[i] * x[i + 1] + beta[i];
        }
        return x;
    }

    public static void main(String[] args) {
        RunsMethod matrix = new RunsMethod();
        int b = 2;
        int n = 4;
        var x = matrix.runsMethod(b, n);
        System.out.print("x = ");
        for (int i = 1; i <= n; i++) {
            System.out.print(x[i] + " ");
        }
    }
}
