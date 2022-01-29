package ru.job4j.design.ocp;


import org.junit.Test;

import javax.xml.bind.JAXBException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ReportXMLTest {

    @Test
    public void testXML() throws JAXBException {
        User user = new User("Иван", "Пятаков");
        MemStore memStore = new MemStore();
        memStore.add(user);
        Report report = new ReportXML(memStore);
        StringBuilder expected = new StringBuilder();
        expected.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\\n")
                .append("<users>\\n")
                .append("    <users>\\n")
                .append("        <lastName>Пятаков</lastName>\\n")
                .append("        <name>Иван</name>\\n")
                .append("    </users>\\n")
                .append("</users>\\n");
        assertThat(report.generate(u -> true), is(expected.toString()));
    }
}