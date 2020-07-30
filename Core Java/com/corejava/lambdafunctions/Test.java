package com.corejava.lambdafunctions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
//        LambdaExample func = ((i)->{System.out.println("Hello world using lamda expression "+i);});
//        func.print(12);

        ArrayList<String> names = new ArrayList<String>();
        List<String> result = new ArrayList<String>();
        names.add("Peter");
        names.add("HP");
        result = names.stream()
                .filter(name -> name.equals(new String("Peter")))
                .collect(Collectors.toList());

        result.forEach(name -> System.out.println(name));
    }
}
