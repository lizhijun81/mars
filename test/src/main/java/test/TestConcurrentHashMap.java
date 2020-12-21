package test;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, String> test = new ConcurrentHashMap<>();
        test.computeIfAbsent("aa", s -> "aaa");

        test.computeIfAbsent("aa", s -> "bbb");

        System.out.println(test);


        List<User> users = Lists.newArrayList();
        users.add(new User("1", "xiaoming"));
        users.add(new User("1", "xiaohong"));
        Map<String, String> collect = users.stream().collect(Collectors.toMap(User::getId, User::getName));
    }

    private static class User {
        private String id;
        private String name;

        public User(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
