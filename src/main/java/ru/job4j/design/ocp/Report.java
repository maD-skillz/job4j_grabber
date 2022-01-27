package ru.job4j.design.ocp;

import ru.job4j.design.srp.Employee;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<User> filter);
}
