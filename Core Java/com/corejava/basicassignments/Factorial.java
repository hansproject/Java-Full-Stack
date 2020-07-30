package com.corejava.basicassignments;

public class Factorial {
    public int computeFactorial(int n){
        if(n==1)
            return n;
        else
            return computeFactorial(n-1)*n;
    }

    public static void main(String[] args) {
        Factorial factorial = new Factorial();
        int n = 4;
        System.out.println(factorial.computeFactorial(n));
    }
}
