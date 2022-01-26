package ru.job4j.design.srp;

import org.junit.Test;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ReportHRTest {

    @Test
    public void whenHRgotSalaryToHigh() {
        MemStore store = new MemStore();
        Employee worker1 = new Employee("Petr", 120);
        Employee worker2 = new Employee("Ivan", 110);
        Employee worker3 = new Employee("Dmitriy", 135);
        store.add(worker1);
        store.add(worker2);
        store.add(worker3);
        ReportHR engineHR = new ReportHR(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(worker3.getName()).append(";")
                .append(worker3.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(worker1.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(worker2.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engineHR.generate(employee -> true), is(expect.toString()));
    }

}