package ru.job4j.design.ocp;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReportXMLTest {

    @Test
    public void XMLtest() {
        User user = new User("Иван", "Пятаков");
        MemStore memStore = new MemStore();
        memStore.add(user);
        Report report = new ReportXML(memStore);
        StringBuilder expected = new StringBuilder();
        expected.append("<html><body><h1>Name; LastName;</h1>")
                .append("<p>Иван</p><p>Пятаков</p></body></html>")
                .append(System.lineSeparator());
        assertThat(report.generate(u -> true), is(expected.toString()));
    }
}