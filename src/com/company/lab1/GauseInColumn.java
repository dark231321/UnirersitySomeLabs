package com.company.lab1;

import com.company.util.FilePaths;
import com.company.util.ParseFileMatrix;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.abs;

public class GauseInColumn {

    public double[] gause(double[][] A, int n) {
        /*  Метод Гаусса c выбором главного элемента    */
        /*------------------------------------------Прямой ход----------------------------------------*/
        /*  Исключения Гаусса основаны на идее последовательного исключения
            переменных по одной до тех пор, пока не останется только одно уравнение с
            одной переменной в левой части. Затем это уравнение решается относительно
            единственной переменной. Таким образом, систему уравнений приводят к
            треугольной (ступенчатой) форме. Для этого среди элементов первого столбца матрицы
            выбирают ненулевой (а чаще максимальный) элемент и перемещают его на крайнее верхнее
            положение перестановкой строк. Затем нормируют все уравнения, разделив его
            на коэффициент a[i,1], где i – номер столбца.                                        */

        int m = n + 1;
        int index; int q=0;
        for (int l = 1; l < n; l++) {
            // Поиск столбца с максимальным a[i][k]
            double max = abs(A[l][l]);        //Первый элемент - кандидат на максимальный
            index = l;                        //Первый столбец кандидат на столбец с максимальным элементом
            for (int i = l + 1; i <= n; i++) {
                if (abs(A[l][i]) > max) {     //Если элемент по модулю больше максимального
                    max = abs(A[l][i]);       //Запоминаем его
                    index = i;                //Запоминаем индекс строки с ним
                }
            }
            for (int j = 1; j <= m; j++) {     //В этом цикле через доп. переменную меняем строки
                double temp = A[l][j];
                A[l][j] = A[index][j];
                A[index][j] = temp;
            }

            q++;

            System.out.println("Результат после шага Q - " + q);
            Arrays.stream(A).forEach(doubles -> System.out.println(Arrays.toString(doubles)));

            double tmp;
            for (int i = l + 1; i <= n; i++) {
                tmp = A[i][l] / A[l][l];
                for (int j = m; j >= l; j--) //Считаем от последнего эл-та в строке, т.е. вектора b
                    A[i][j] -= tmp * A[l][j];
            }
        }
        /*------------------------------------------Прямой ход----------------------------------------*/



        /*-------------------------------Обратный ход-------------------------------*/
        /*    Обратная подстановка предполагает
              подстановку полученного на предыдущем шаге значения
              переменной xn в предыдущие уравнения:                              */

        double[] x = new double[m];
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
        GauseInColumn gauseInColumn = new GauseInColumn();
        var A = ParseFileMatrix.parseMatrixFromFile(FilePaths.pathToMatrixFile);
        System.out.println(Arrays.toString(gauseInColumn.gause(A, A.length - 1)));
    }
}
