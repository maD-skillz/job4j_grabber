package ru.job4j.design.ocp;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReportJSONTest {

    @Test
    public void testJSON() {
        User user = new User("Иван", "Пятаков");
        MemStore memStore = new MemStore();
        memStore.add(user);
        Report report = new ReportJSON(memStore);
        StringBuilder expected = new StringBuilder();
        expected.append("{\"Иван\": \"Пятаков\"}")
        .append(System.lineSeparator());
        assertThat(report.generate(u -> true), is(expected.toString()));
    }

}