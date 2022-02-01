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

public class ReportXMLTest {

    @Test
    public void testXML() throws JAXBException {
        Employee employee = new Employee(
                "Иван",
                new GregorianCalendar(2019, Calendar.JUNE, 15),
                new GregorianCalendar(2020, Calendar.DECEMBER, 31),
                1000D);
        MemStore memStore = new MemStore();
        memStore.add(employee);
        Report report = new ReportXML(memStore);
        StringBuilder expected = new StringBuilder();
        expected.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n")
                .append("<employees>\n")
                .append("    <employees>\n")
                .append("        <fired>2020-12-31T00:00:00+03:00</fired>\n")
                .append("        <hired>2019-06-15T00:00:00+03:00</hired>\\n")
                .append("        <name>Иван</name>\n")
                .append("        <salary>1000.0</salary>\n")
                .append("    </employees>\n")
                .append("</employees>\n");
        assertThat(report.generate(u -> true), is(expected.toString()));
    }
}