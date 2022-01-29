package ru.job4j.design.ocp;


import javax.xml.bind.JAXBException;
import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<User> filter) throws JAXBException;
}
