package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportUSDollar implements Report {

    private Store store;

    private int course = 79;

    public ReportUSDollar(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getSalary() / course).append("$ ;")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
