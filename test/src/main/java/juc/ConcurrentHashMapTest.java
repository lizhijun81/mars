package juc;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> c = new ConcurrentHashMap<>();
        c.put("k1","v1");
        c.put("k2","v2");
    }

}
