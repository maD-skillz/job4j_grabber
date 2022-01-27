package ru.job4j.design.ocp;

import ru.job4j.design.srp.Employee;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    List<User> findBy(Predicate<User> filter);
}
