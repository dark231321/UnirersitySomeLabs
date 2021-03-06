package com.company.lab1;

import com.company.FilePaths;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Matrix {

    private double[][] parseMatrixFromFile(String fileName){
        File file = new File(fileName);
        double[][] value = null;
        try {
            Scanner scanner = new Scanner(file);
            String[] temp = scanner.nextLine().split(" ");
            int nMatrix = Integer.parseInt(temp[0]);
            int m = nMatrix + 1;

            value = new double[nMatrix + 1][m + 1];
            for (int i = 1; i <= nMatrix; i++) {
                String[] numbers = scanner.nextLine().split(" ");
                for (int j = 1; j <= m; j++) {
                    value[i][j] = Double.parseDouble(numbers[j - 1]);
                }
            }
            scanner.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return value;
    }

    private double[] methodForward(int n, double[][] A){
        int m = n + 1;
        double tmp;
        double[] x = new double[n + 1];

        for (int k = 1; k < n; k++) {
            for (int i = k + 1; i <= n; i++) {
                tmp = A[i][k] / A[k][k];
                for (int j = m; j >= k; j--) //Считаем от последнего эл-та в строке, т.е. базиса
                    A[i][j] -= tmp * A[k][j];
            }
        }

        x[n] = A[n][m] / A[n][n];
        for (int i = n - 1; i >= 1; i--) {
            x[i] = A[i][m];
            for (int j = i + 1; j <= n; j++)
                x[i] -= A[i][j] * x[j];
            x[i] = x[i] / A[i][i];
        }

        return x;
    }

    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        double[][] A = matrix.parseMatrixFromFile(FilePaths.pathToMatrixFile);
        Arrays.stream(A).forEach(doubles -> System.out.println(Arrays.toString(doubles)));
        Arrays.stream(matrix.methodForward(A.length - 1, A)).forEach(System.out::println);
    }


}
