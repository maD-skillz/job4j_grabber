package ru.job4j.grabber;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ru.job4j.grabber.html.SqlRuParse;

import java.io.*;
import java.sql.*;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Grabber implements Grab {
    private final Properties cfg = new Properties();

    public Store store() {
        return null;
    }

    public Scheduler scheduler() throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        return scheduler;
    }

    public void cfg() throws IOException {
        try (InputStream in = new FileInputStream("grabber.properties")) {
            cfg.load(in);
        }
    }

    public static Connection getCon(Properties properties) throws SQLException {
        return DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password")
        );
    }

    @Override
    public void init(Parse parse, Store store, Scheduler scheduler) throws SchedulerException {
        JobDataMap data = new JobDataMap();
        data.put("store", store);
        data.put("parse", parse);
        JobDetail job = newJob(GrabJob.class)
                .usingJobData(data)
                .build();
        SimpleScheduleBuilder times = simpleSchedule()
                .withIntervalInSeconds(Integer.parseInt(cfg.getProperty("time")))
                .repeatForever();
        Trigger trigger = newTrigger()
                .startNow()
                .withSchedule(times)
                .build();
        scheduler.scheduleJob(job, trigger);
    }

    public static class GrabJob implements Job {

        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobDataMap map = context.getJobDetail().getJobDataMap();
            Store store = (Store) map.get("store");
            Parse parse = (Parse) map.get("parse");
            Connection connection = (Connection) context.getJobDetail().getJobDataMap().get("parse");
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO post(title, link, description, created) VALUES(?, ?, ?, ?);",
                    Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, store.findById(Statement.RETURN_GENERATED_KEYS).getTitle());
                ps.setString(2, store.findById(Statement.RETURN_GENERATED_KEYS).getLink());
                ps.setString(3, store.findById(Statement.RETURN_GENERATED_KEYS).getDescription());
                ps.setTimestamp(4, Timestamp.valueOf(store.findById(Statement.RETURN_GENERATED_KEYS).getCreated()));
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Grabber grab = new Grabber();
        PsqlStore psqlStore;
        grab.cfg();
        Scheduler scheduler = grab.scheduler();
        Store store = grab.store();
        grab.init(new SqlRuParse(), store, scheduler);
    }
}