package ru.job4j.design.srp;

import java.util.function.Predicate;

public class ReportEngine implements Report {

    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("<html><body><h1>Name; Hired; Fired; Salary;</h1>");
        for (Employee employee : store.findBy(filter)) {
            text.append("<p>")
                    .append(employee.getName()).append("</p>")
                    .append("<p>")
                    .append(employee.getHired()).append("</p>")
                    .append("<p>")
                    .append(employee.getFired()).append("</p>")
                    .append("<p>")
                    .append(employee.getSalary()).append("</p>")
                    .append("</body>")
                    .append("</html>")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
