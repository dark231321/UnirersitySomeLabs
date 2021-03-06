package com.company.lab0;

import com.company.FilePaths;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TriangularMatrix {

    private double[][] parseMatrixFromFile(){
        File file = new File(FilePaths.pathToTriangularMatrixFile);
        double[][] value = null;
        try {
            Scanner scanner = new Scanner(file);
            String[] temp = scanner.nextLine().split(" ");
            int n = Integer.parseInt(temp[0]);
            int m = n + 1;

            value = new double[n + 1][m + 1];
            for (int i = 1; i <= n; i++) {
                String[] numbers = scanner.nextLine().split(" ");
                for (int j = 1; j <= m; j++) {
                    value[i][j] = Double.parseDouble(numbers[j - 1]);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return value;
    }

    private double[] methodHigher(int n, double[][] A){
        double[] x = new double[n + 1];

        x[n] = A[n][n + 1]/A[n][n];
        for (int i = n - 1; i >= 1; i--) {
            x[i] = A[i][n + 1];
            for (int j = i + 1; j <= n; j++)
                x[i] -= A[i][j] * x[j];
            x[i] = x[i]/A[i][i];
        }
        return x;
    }

    private double[] methodLower(int n, double[][] A){
        double[] x = new double[n + 1];

        x[1] = A[1][n + 1]/A[1][1];
        for (int i = 2; i <= n; i++) {
            x[i] = A[i][n + 1];
            for (int j = i - 1; j >= 1; j--)
                x[i] -= A[i][j] * x[j];
            x[i] = x[i]/A[i][i];
        }
        return x;
    }

    public static void main(String[] args) {
        TriangularMatrix triangularMatrix = new TriangularMatrix();
        double[][] A = triangularMatrix.parseMatrixFromFile();
        System.out.println("Input matrix:");
        Arrays.stream(A).forEach(doubles -> System.out.println(Arrays.toString(doubles)));
        System.out.println("Method result:");
        System.out.println(Arrays.toString(triangularMatrix.methodLower(A.length - 1, A)));
    }
}
