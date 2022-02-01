package ru.job4j.design.ocp;

import org.junit.Test;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.MemStore;
import ru.job4j.design.srp.Report;

import javax.xml.bind.JAXBException;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JsonBuilderTest {

    @Test
    public void whenJSON() throws JAXBException {
        Calendar hired = new GregorianCalendar(2019, Calendar.JUNE, 15);
        Calendar fired = new GregorianCalendar(2020, Calendar.DECEMBER, 31);
        Employee employee = new Employee("Иван", hired, fired, 1000D);
        MemStore memStore = new MemStore();
        memStore.add(employee);
        Report report = new JsonBuilder(memStore);
        String expected = "[{\"name\":\"Иван\",\"hired\":{\"year\":2019,\"month\":5,\"dayOfMonth\":15,"
                + "\"hourOfDay\":0,\"minute\":0,\"second\":0},\"fired\":{\"year\":2020,\"month\":11,"
                + "\"dayOfMonth\":31,\"hourOfDay\":0,\"minute\":0,\"second\":0},\"salary\":1000.0}]";
        assertThat(report.generate(u -> true), is(expected));
    }

}