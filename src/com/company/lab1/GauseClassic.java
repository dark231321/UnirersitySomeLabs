package com.company.lab1;

import com.company.util.FilePaths;
import com.company.util.ParseFileMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class GauseClassic {

    public double[] gause(double[][] A, int n){
        int m = n + 1;
        System.out.println("A before: ");
        Arrays.stream(A).forEach(doubles -> System.out.println(Arrays.toString(doubles)));
        /*Метод Гаусса*/
        /*---------------------------------Прямой ход----------------------------------*/
        /*Исключения Гаусса основаны на идее последовательного исключения
        переменных по одной до тех пор, пока не останется только одно уравнение с
        одной переменной в левой части. Затем это уравнение решается относительно
        единственной переменной. Таким образом, систему уравнений приводят к
        треугольной (ступенчатой) форме.                                         */
        double tmp;
        double[] x = new double[n+1];
        for (int k = 1; k < n; k++) {
            for (int i = k + 1; i <= n; i++) {
                tmp = A[i][k]/A[k][k];
                for (int j = m; j >= k; j--) //Считаем от последнего эл-та в строке, т.е. базиса
                    A[i][j] -= tmp * A[k][j]; //

            }
        }

        System.out.println("A after:");
        Arrays.stream(A).forEach(doubles -> System.out.println(Arrays.toString(doubles)));

        /*-------------------------------Обратный ход-------------------------------*/
/*    Обратная подстановка предполагает
        подстановку полученного на предыдущем шаге значения
         переменной xn в предыдущие уравнения:                              */

        x[n] = A[n][m]/A[n][n];
        for (int i = n - 1; i >= 1; i--) {
            x[i] = A[i][m];
            for (int j = i + 1; j <= n; j++)
                x[i] -= A[i][j] * x[j];
            x[i] = x[i]/A[i][i];
        }
        System.out.println("Result: ");
        System.out.println(Arrays.toString(x));
        /*-------------------------------Обратный ход-------------------------------*/
        return x;
    }

    public static void main(String[] args) {
        GauseClassic matrix = new GauseClassic();
        double[][] A = ParseFileMatrix.parseMatrixFromFile(FilePaths.pathToMatrixFile);

        matrix.gause(A, A.length - 1);
    }
}
