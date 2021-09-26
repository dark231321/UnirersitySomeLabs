package com.company.util;

import java.util.Arrays;

public final class MatrixInfo {
    private final int n;
    private final double[][] A;
    private final double[] x;
    private final double[] b;


    public MatrixInfo(double[][] a, double[] x, double[] b) {
        A = a;
        this.x = x;
        this.b = b;
        n = a.length - 1;
    }

    public int getN() {
        return n;
    }

    public double[][] getA() {
        return A;
    }

    public double[] getX() {
        return x;
    }

    public double[] getB() {
        return b;
    }

    @Override
    public String toString() {
        return "Jacobi" +
                "\nn=" + n +
                "\nA:" + Arrays.stream(A)
                                 .reduce(new StringBuffer(""),
                                         (s, doubles) -> s.append(Arrays.toString(doubles)).append("\n"),
                                         StringBuffer::append)
                                .toString() +
                "x:" + Arrays.toString(x) +
                "\nb:" + Arrays.toString(b) +
                "\n";
    }
}
