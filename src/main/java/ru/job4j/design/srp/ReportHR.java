package ru.job4j.design.srp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReportHR implements Report {

    private Store store;

    public ReportHR(Store store) {
        this.store = store;
    }



    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> list = store.findBy(filter).stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed()).collect(Collectors.toList());
        StringBuilder builder = new StringBuilder();
        builder.append("Name; Salary;");
        for (Employee emp : list) {
            builder.append(emp.getName()).append(";")
                    .append(emp.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}
