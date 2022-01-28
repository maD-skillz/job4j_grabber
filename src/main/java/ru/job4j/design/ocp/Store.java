package ru.job4j.design.ocp;


import java.util.List;
import java.util.function.Predicate;

public interface Store {
    List<User> findBy(Predicate<User> filter);
}
