package com.company.lab1;

import com.company.util.FilePaths;
import com.company.util.ParseFileMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.*;

public class MethodSquareRoot {

    private final double DESIRED_PRECISION = E-10;

    private double sign(double x) {
        if(abs(x) <= DESIRED_PRECISION)
            return 0;
        return Double.compare(x, DESIRED_PRECISION);
    }

    private double[] methodSquareRoot(double[][] A, int n){
        int m = n + 1;
        /*    A = S*DS,
              S — верхняя треугольная матрица с положительными элементами на главной диагонали,
              S*— транспонированная матрица S,
              D — диагональная матрица, на диагонали которой находятся числа, равные ± 1*/
        double[][] S = new double[m][m];   //Описываем матрицу S
        double[][] D = new double[m][m];   //Описываем матрицу D

        S[1][1] = sqrt(abs(A[1][1]));
        D[1][1] = signum(A[1][1]);
        for(int j = 2; j <= n; ++j) {
            S[1][j] = A[1][j] / (S[1][1] * D[1][1]);
        }

        /*Все элементы матрицы S считаются по следующей схеме:
          s[i,j]= (A[i,j] - sum(от l=1 до l=i-1)(s[l,i]*s[l,j]*D[l,l]))/s[i,i]*D[i,i]
          А все элементы диагональной матрицы D считаются следующим образом:
          D[i,i]= sign(A[i,j] - sum(от l=1 до l=i-1)(S[l][i] * S[l][i] * D[l][l]
        /*------------------------  Инициализация первых элементов матриц S и D------------------------*/

        /*------------------------  Расчитывамем все оставшиеся значения S и D------------------------*/
        for(int i = 2; i <= n; ++i) {
            double sum = 0;
            for (int l = 1; l <= i - 1; ++l)
                sum = sum + S[l][i] * S[l][i] * D[l][l];
            S[i][i] = sqrt(abs(A[i][i] - sum));
            D[i][i] = sign(A[i][i] - sum);
            for (int j = i + 1; j <= n; ++j) {
                sum = 0;
                for (int l = 1; l <= i - 1; l++) {
                    sum = sum + S[l][i] * S[l][j] * D[l][l];
                }
                S[i][j] = (A[i][j] - sum) / (S[i][i]* D[i][i]);
            }
        }

        /*------------------------Расчитывамем все оставшиеся значения S и D------------------------*/

        /*Выводим матрицу после прямого хода, чтобы проверить, что она была приведена
          к ступенчатому виду       */

        Arrays.stream(A).forEach(doubles -> System.out.println(Arrays.toString(doubles)));

        /*-------------------------------------Обратный ход-------------------------------------*/
        /*Обратный ход состоит в в последовательном решении двух систем уравнений
                             (1) S*y = f
          где f это последний столбец в расширенной матрице (вектор)
          в нашем случае это переменная вида A[i,m], где m это последний стобец матрицы
          Формула для нахождения y[i]
                y[i] = (f[i] - sum(от l=1 до l=i-1)(s[l,i]*y[l]))/s[i,i], где i = 2,3,..,m */

        double[] y = new double[n + 1];
        y[1] = A[1][m] / (S[1][1]* D[1][1]);        //y[1] всегда равен f[1]/s[1,1]
        for(int i = 2; i <= n; ++i) {
            double sum = 0;
            for (int l = 1; l <= i - 1; ++l)
                sum = sum + S[l][i] * y[l]* D[l][l];
            y[i] = (A[i][m] - sum) / (S[i][i]* D[i][i]);
        }
        System.out.println(Arrays.toString(y));
        /*Используя значения y, найденные в предыдущем уравнении, решаем второе уравнение
                             (2) Sx = y
          формула для нахождения x[i]
                             x[i] = (y[i] - sum(от l=1+1 до l=i)(s[i,l]*x[l]))/s[i,i], гдe i = i-1,i-2,..,1 */
        double[] x =  new double[n + 1];
        x[n] = y[n] / S[n][n];           //Последний x[n] всегда равен u[n]/s[n,n]
        for(int i = n - 1; i >= 1; --i) {
            double sum = 0;
            for (int l = i + 1; l <= n; ++l)
                sum = sum + S[i][l] * x[l];
            x[i] = (y[i] - sum) / S[i][i];
        }

        System.out.println(Arrays.toString(x));
        return x;
    }

    public static void main(String[] args) {
        MethodSquareRoot matrix = new MethodSquareRoot();
        var A = ParseFileMatrix.parseMatrixFromFile(FilePaths.pathToSquare);
        Arrays.stream(A).forEach(doubles -> System.out.println(Arrays.toString(doubles)));
        matrix.methodSquareRoot(A, A.length - 1);
    }

}
