package com.company.lab0;

public class Eps {
    public static void main(String[] args) {
        double eps = 1;
        while (1 + eps > 1){
            System.out.println(eps);
            eps /= 2;
        }
        System.out.println(eps);
    }
}
