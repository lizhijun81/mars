package test;

import java.util.LinkedHashMap;

public class TestLinkedHashMap {

    public static void main(String[] args) {
        LinkedHashMap<Integer, String> result = new LinkedHashMap<>(1);

        result.put(1, "234");

        result.put(3, "2345");

        System.out.println(result);
    }

}
