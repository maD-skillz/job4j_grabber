package ru.job4j.design.ocp;


import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<User> filter);
}
