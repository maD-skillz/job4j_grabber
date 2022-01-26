package ru.job4j.design.srp;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;
import java.util.Calendar;

public class ReportEngineTest {

    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("<h1>Name; Hired; Fired; Salary;</h1>")
                .append("<html>")
                .append("<body>").append("<p>")
                .append(worker.getName()).append("</p>")
                .append("<p>")
                .append(worker.getHired()).append("</p>")
                .append("<p>")
                .append(worker.getFired()).append("</p>")
                .append("<p>")
                .append(worker.getSalary()).append("</p>")
                .append("</body>")
                .append("</html>")
                .append(System.lineSeparator());
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}