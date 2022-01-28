package ru.job4j.design.ocp;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JsonBuilderTest {

    @Test
    public void whenJSON() {
        User user = new User("Иван", "Пятаков");
        MemStore memStore = new MemStore();
        memStore.add(user);
        Report report = new JsonBuilder(memStore);
        String exp = "ru.job4j.design.ocp.User@3c1";
        StringBuilder expected = new StringBuilder();
        expected.append("{\"Иван\": \"Пятаков\"}")
                .append(System.lineSeparator());
        assertThat(report.generate(u -> true), is("ru.job4j.design.ocp.User@3c1"));
    }

}