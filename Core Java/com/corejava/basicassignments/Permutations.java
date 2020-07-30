package com.corejava.basicassignments;

public class Permutations {

    public void displayPermutation(String str){

        //System.out.println(permute(0,str.length()-1,str));
    }

    public void permute(int start,int end, String str){
        if(start == end) {
            System.out.print(str+" ");
        }
        else {
            for (int i = start; i <= end; i++) {
                str = swap(start,i,str);
                permute(start+1, end, str);
            //    str = swap(start,i,str);// Swap back
            }
        }
    }

    public String swap(int l, int r, String str){
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i=0; i<str.length();i++){
            if(i==l)
                stringBuffer.append(str.charAt(r));
            else if(i==r)
                stringBuffer.append(str.charAt(l));
            else
                stringBuffer.append(str.charAt(i));
        }

        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        String input = "ABC";
        int n = input.length();
        Permutations permutations = new Permutations();
    //    permutations.displayPermutation(input);
        permutations.permute(0,n-1, input);
        //System.out.println(permutations.swap(0,3,input));

    }
}
