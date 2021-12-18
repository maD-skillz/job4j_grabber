package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static java.lang.System.getProperty;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    public static int res = 0;

    private static Connection cn;

    public static Properties init() {
        Properties config = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader().
                getResourceAsStream("src/main/resources/rabbit.properties")) {
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return config;
    }

    public static Connection getCon(Properties properties) throws SQLException {
        cn = DriverManager.getConnection(
                getProperty("url"),
                getProperty("username"),
                getProperty("password")
        );
        return cn;
    }

    public static Integer getInterval() throws IOException {
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
        Properties initCon = init();
        try (Connection connection = getCon(initCon)) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "INSERT INTO rabbit(name) VALUES(white);"
                );
                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.start();
                JobDataMap data = new JobDataMap();
                data.put("connection", connection);
                JobDetail job = JobBuilder.newJob(Rabbit.class)
                        .usingJobData(data)
                        .build();
                SimpleScheduleBuilder times = simpleSchedule()
                        .withIntervalInSeconds(getInterval())
                        .repeatForever();
                statement.execute(sql);
                Trigger trigger = newTrigger()
                        .startNow()
                        .withSchedule(times)
                        .build();
                scheduler.scheduleJob(job, trigger);
                Thread.sleep(10000);
                scheduler.shutdown();
            }
        } catch (SchedulerException | InterruptedException | SQLException se) {
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
