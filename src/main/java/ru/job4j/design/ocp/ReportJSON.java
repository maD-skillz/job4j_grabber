package ru.job4j.design.ocp;

import java.util.function.Predicate;

public class ReportJSON implements Report {

    private Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<User> filter) {
        String doubleD = "\"";
        StringBuilder builder = new StringBuilder();
        for (User user : store.findBy(filter)) {
            builder.append("{")
                    .append("\"")
                    .append(user.getName())
                    .append(doubleD)
                    .append(": ")
                    .append(doubleD)
                    .append(user.getLastName())
                    .append(doubleD)
                    .append("}")
                    .append(System.lineSeparator());
        }
        return builder.toString();
    }
}
