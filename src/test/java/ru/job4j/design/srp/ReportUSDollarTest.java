package ru.job4j.design.srp;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReportUSDollarTest {

    @Test
    public void whenSalaryInUSDollars() throws JAXBException {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportUSDollar(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(worker.getName()).append(";")
                .append(worker.getSalary() / 79).append("$ ;")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }

}
