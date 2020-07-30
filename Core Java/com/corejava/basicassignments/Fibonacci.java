package com.corejava.basicassignments;

public class Fibonacci {

    public int computeFibonacci(int n){
        if(n==0)
            return 0;
        if(n==1)
            return 1;
        else
            return computeFibonacci(n-1)+computeFibonacci(n-2);
    }

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        int n = 5;
        System.out.println(fibonacci.computeFibonacci(n));
    }
}
