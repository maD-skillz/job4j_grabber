package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    public static int res = 0;

    private static Connection cn;

    public static void init() {
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("src/main/resources/rabbit.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static Integer load() throws IOException {
        try (BufferedReader rd = new BufferedReader(new FileReader("src/main/resources/rabbit.properties"))) {
            rd.lines().forEach(e -> {
                if (e.contains("interval")) {
                    String[] el = e.split("=");
                    if (!el[1].isEmpty() && el.length == 2) {
                        res = Integer.parseInt(el[1]);
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            });
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        try {
            List<Long> store = new ArrayList<>();
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            init();
            JobDataMap data = new JobDataMap();
            data.put("INSERT INTO rabbit(name) VALUES(white);", store);
            JobDetail job = JobBuilder.newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(load())
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            Thread.sleep(10000);
            scheduler.shutdown();
        } catch (SchedulerException | InterruptedException se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {

        public Rabbit() {
            System.out.println(hashCode());
        }

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
        }
    }
}
