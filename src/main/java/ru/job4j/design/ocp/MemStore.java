package ru.job4j.design.ocp;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MemStore implements Store {

    private final List<User> users = new ArrayList<>();

    public void add(User user) {
        users.add(user);
    }

    @Override
    public List<User> findBy(Predicate<User> filter) {
        return users.stream().filter(filter).collect(Collectors.toList());
    }
}
