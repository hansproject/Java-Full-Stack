package com.corejava.collection;

import java.util.Iterator;
import java.util.LinkedHashMap;
public class LinkedHashMapExample {
    public static void main(String[] args) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();

        linkedHashMap.put(2,"Hp");
        linkedHashMap.put(1,"Me");
        linkedHashMap.put(3,"e");

        Iterator iterator = linkedHashMap.entrySet().iterator();

        //Print each element
        // LinkedHashMap retains insertion order
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        // Or print all
        // System.out.println(linkedHashMap);
    }
}
