package com.company.util;

public class RelaxationMatrixInfo extends MatrixInfo {
    private final float omega;

    public RelaxationMatrixInfo(float eps, double[][] a, double[] x, double[] b, float omega) {
        super(eps, a, x, b);
        this.omega = omega;
    }

    public float getOmega() {
        return omega;
    }
}
