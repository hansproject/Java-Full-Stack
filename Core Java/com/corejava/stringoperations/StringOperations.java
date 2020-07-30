package com.corejava.stringoperations;

import java.util.ArrayList;

public class StringOperations {

    public StringOperations(){super();}

    public int wordCount(String str[], String wordToCount){

        int count = 0;
        for (int i=0;i<str.length;i++) {
            if(str[i].equals(wordToCount)){
                count++;
            }
        }
        return count;
    }

    public ArrayList<String> removeDup(String str[]){

        ArrayList<String> result = new ArrayList<String>();

        for(int i=0;i<str.length;i++) {
            if(!result.contains(str[i])){
                result.add(str[i]);
            }
        }
        return result;
    }
    public static void main(String[]rgs) {
        String input = "I am a Tennis Player and I will play Tennis";
//        StringTokenizer stringTokenizer = new StringTokenizer(input);
        StringOperations stringOperations = new StringOperations();

        String a[]     = input.split("\\s");
        String noDup[] = new String[stringOperations.removeDup(a).size()];
        noDup =  stringOperations.removeDup(a).toArray(noDup);

        int countPerWord = 0;

        for (int i=0;i<noDup.length;i++) {
            countPerWord = stringOperations.wordCount(a,noDup[i]);
            System.out.println(noDup[i]+": "+countPerWord);
        }
//        while(stringTokenizer.hasMoreElements()){
//          System.out.println(stringTokenizer.nextElement());
//       }
    }
}
