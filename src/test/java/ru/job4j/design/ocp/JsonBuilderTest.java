package ru.job4j.design.ocp;

import org.junit.Test;

import javax.xml.bind.JAXBException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JsonBuilderTest {

    @Test
    public void whenJSON() throws JAXBException {
        User user = new User("Иван", "Пятаков");
        MemStore memStore = new MemStore();
        memStore.add(user);
        Report report = new JsonBuilder(memStore);
        String expected = "{\"name\":\"Иван\",\"lastName\":\"Пятаков\"}";
        assertThat(report.generate(u -> true), is(expected));
    }

}