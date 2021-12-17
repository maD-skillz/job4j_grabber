package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {

    public static int res = 0;

    public static Integer load() throws IOException {
        try (BufferedReader rd = new BufferedReader(new FileReader("src/main/resources/rabbit.properties"))) {
            rd.lines().forEach(e -> {
                String[] el = e.split("=");
                if (!el[1].isEmpty() && el.length == 2) {
                    res = Integer.parseInt(el[1]);
                } else {
                    throw  new IllegalArgumentException();
                }
            });
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = JobBuilder.newJob(Rabbit.class).build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(load())
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
        }
    }
}
