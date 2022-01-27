package ru.job4j.design.ocp;

import java.util.function.Predicate;

public class ReportXML implements Report {

    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<User> filter) {
        StringBuilder builder = new StringBuilder();
        for (User user : store.findBy(filter)) {
            builder.append("<html><body><h1>Name; LastName;</h1>")
            .append("<p>")
            .append(user.getName())
            .append("</p>")
            .append("<p>")
            .append(user.getLastName())
            .append("</p>")
            .append("</body>")
            .append("</html>")
            .append(System.lineSeparator());
        }
        return builder.toString();
    }
}
