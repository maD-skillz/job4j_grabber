package ru.job4j.design.ocp;

import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Report;
import ru.job4j.design.srp.Store;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringWriter;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class ReportXML implements Report {

    private Store store;

    public ReportXML(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Employees.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(new Employees(store.findBy(filter)), writer);
            xml = writer.getBuffer().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return xml;
    }


    @XmlRootElement(name = "employees")
    public static class Employees {

        private List<Employee> employees;

        public Employees() {

        }

        public Employees(List<Employee> employees) {
            this.employees = employees;
        }

        public List<Employee> getEmployees() {
            return employees;
        }

        public void setEmployees(List<Employee> employees) {
            this.employees = employees;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Employees)) {
                return false;
            }
            Employees employees1 = (Employees) o;
            return employees.equals(employees1.employees);
        }

        @Override
        public int hashCode() {
            return Objects.hash(employees);
        }
    }
}

