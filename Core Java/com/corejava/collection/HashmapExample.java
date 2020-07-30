package com.corejava.collection;

import java.util.Iterator;
import java.util.HashMap;

public class HashmapExample {
    public static void main(String[] args) {
        HashMap hashmap = new HashMap();

        hashmap.put(2,"Hp");
        hashmap.put(1,"Me");
        hashmap.put(3,"e");

        Iterator iterator = hashmap.entrySet().iterator();

        //Print each element
        // HashMap store element in order using key values
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        // Or print all
        // System.out.println(hashmap);
    }
}
