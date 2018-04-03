package java8;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8 {
    public static void main(String[] args) {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

//        Collections.sort(names, Comparator.naturalOrder());

        long count = stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .count();//完结操作
//                .forEach(System.out::println);//完结操作
        System.out.println(count);

//        //并行流和串行流
//        int max = 1000000;
//        List<String> values = new ArrayList<>(max);
//        for (int i = 0; i < max; i++) {
//            UUID uuid = UUID.randomUUID();
//            values.add(uuid.toString());
//        }
//
//        long t0 = System.nanoTime();
//        values.stream().sorted();
//        long t1 = System.nanoTime();
//        System.out.println(String.format("sequential sort took: %d ms", t1 - t0));
//
//
//
//        long t2 = System.nanoTime();
//        values.parallelStream().sorted();
//        System.out.println(count);
//        long t3 = System.nanoTime();
//        System.out.println(String.format("parallel sort took: %d ms", t3 - t2));


//        Function<String, Integer> toInteger = Integer::valueOf;
//        Function<String, String> backToString = toInteger.andThen(String::valueOf);
//
//        String apply = backToString.apply("123");

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(e -> {
                    System.out.println("filter: " + e);
                    return true;
                })
                .forEach(e -> System.out.println("forEach: " + e));
        System.out.println("=====================");
        Stream.of("d2", "b1", "b3", "c", "a2")
                .filter(e -> {
                    System.out.println("filter:" + e);
                    return e.startsWith("a");
                })
                .map(e -> {
                    System.out.println("map:" + e);
                    return e.toUpperCase();
                })
                .anyMatch(e -> {
                    System.out.println("anyMatch:" + e);
                    return e.startsWith("A");
                });


        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));


        List<Person> filtered = persons
                        .stream()
                        .filter(p -> p.name.startsWith("P"))
                        .collect(Collectors.toList());
        System.out.println(filtered);    // [Peter, Pamela]

        IntSummaryStatistics ageSummary =
                persons
                        .stream()
                        .collect(Collectors.summarizingInt(p -> p.age));


    }
}

