package com.corejava.basicassignments;

public class PrimeNumberMethods {

    public boolean isPrime(int div, int input){
        if(div == 2)
            return true;
        if(input%div==0)
            return false;
        else
            return isPrime(div-1,input);
    }

    public static void main(String[] args) {
        PrimeNumberMethods primeNumberMethods = new PrimeNumberMethods();

        // Starting divider
        int input = 10;
        int divider = input-1;

        boolean result = primeNumberMethods.isPrime(divider,input);
        if(result!=false)
            System.out.println("It is a prime number...");
        else
            System.out.println("Not a prime number");
    }
}
