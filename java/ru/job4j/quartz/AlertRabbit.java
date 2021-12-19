package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;

import static java.lang.System.getProperty;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    private final static LocalDateTime TIME = LocalDateTime.now();

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
        return DriverManager.getConnection(
                getProperty("url"),
                getProperty("username"),
                getProperty("password")
        );
    }

    public static void main(String[] args) throws IOException {
        Properties initCon = init();
        try (Connection connection = getCon(initCon)) {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.start();
                JobDataMap data = new JobDataMap();
                data.put("connection", connection);
                JobDetail job = JobBuilder.newJob(Rabbit.class)
                        .usingJobData(data)
                        .build();
                SimpleScheduleBuilder times = simpleSchedule()
                        .withIntervalInSeconds(connection.getNetworkTimeout())
                        .repeatForever();
                Trigger trigger = newTrigger()
                        .startNow()
                        .withSchedule(times)
                        .build();
                scheduler.scheduleJob(job, trigger);
                Thread.sleep(10000);
                scheduler.shutdown();
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
            Connection connection = (Connection) context.getJobDetail().getJobDataMap().get("connection");
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO rabbit(name, created_date) VALUES(?, ?);",
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, "White");
                ps.setTimestamp(2, Timestamp.valueOf(TIME));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Rabbit runs here ...");
        }
    }
}
