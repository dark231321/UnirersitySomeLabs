package com.company.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParseFileMatrix {

    public static double[][] parseMatrixFromFile(String fileName){
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static MatrixInfo parseMatrixInfo(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            String[] temp = scanner.nextLine().split(" ");
            int nMatrix = Integer.parseInt(temp[0]);
            int m = nMatrix + 1;
            float eps = Float.parseFloat(scanner.nextLine());

            var A = new double[nMatrix + 1][nMatrix + 1];
            var b = new double[nMatrix+1];
            var x = new double[nMatrix+1];

            for (int i = 1; i <= nMatrix; i++) {
                String[] numbers = scanner.nextLine().split(" ");
                for (int j = 1; j < m; j++) {
                    A[i][j] = Double.parseDouble(numbers[j - 1]);
                }
                b[i] = Double.parseDouble(numbers[m - 1]);
                x[i] = b[i]/ A[i][i];
            }
            scanner.close();
            return new MatrixInfo(eps, A, x, b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Filepath doesn't exist" + fileName, e);
        }
    }

    public static RelaxationMatrixInfo parseRelaxation(String filename) {
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            String[] temp = scanner.nextLine().split(" ");
            int nMatrix = Integer.parseInt(temp[0]);
            int m = nMatrix + 1;
            float eps = Float.parseFloat(scanner.nextLine());
            float omega = Float.parseFloat(scanner.nextLine());
            var A = new double[nMatrix + 1][nMatrix + 1];
            var b = new double[nMatrix+1];
            var x = new double[nMatrix+1];

            for (int i = 1; i <= nMatrix; i++) {
                String[] numbers = scanner.nextLine().split(" ");
                for (int j = 1; j < m; j++) {
                    A[i][j] = Double.parseDouble(numbers[j - 1]);
                }
                b[i] = Double.parseDouble(numbers[m - 1]);
                x[i] = b[i]/ A[i][i];
            }
            scanner.close();
            return new RelaxationMatrixInfo(eps, A, x, b, omega);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Filepath doesn't exist" + filename, e);
        }

    }
}
