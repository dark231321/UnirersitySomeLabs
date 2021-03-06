package com.company.lab0;

import com.company.FilePaths;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class TriangularMatrix {


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

    private double[] method(int n, double[][] A){
        int m = n + 1;
        double[] x = new double[n + 1];

        x[n] = A[n][m]/A[n][n];
        for (int i = n - 1; i >= 1; i--) {
            x[i] = A[i][m];
            for (int j = i + 1; j <= n; j++)
                x[i] -= A[i][j] * x[j];
            x[i] = x[i]/A[i][i];
        }
        return x;
    }


    public static void main(String[] args) {
        TriangularMatrix triangularMatrix = new TriangularMatrix();
        double[][] A = triangularMatrix.parseMatrixFromFile(FilePaths.pathToTriangularMatrixFile);
        Arrays.stream(A).forEach(doubles -> System.out.println(Arrays.toString(doubles)));
        System.out.println(Arrays.toString(triangularMatrix.method(A.length - 1, A)));
    }
}
