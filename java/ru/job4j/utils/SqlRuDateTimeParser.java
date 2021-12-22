package ru.job4j.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static String yestDay() {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT + 3"));
        cal.add(Calendar.DATE, -1);
        return cal.getTime().toString();
    }

    private static String today() {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT + 3"));
        return cal.getTime().toString();
    }

    private static final Map<String, String> MONTHS = Map.ofEntries(Map.entry("янв", "1"), Map.entry("фев", "2"),
            Map.entry("мар", "3"), Map.entry("апр", "4"), Map.entry("май", "5"), Map.entry("июн", "6"),
            Map.entry("июл", "7"), Map.entry("авг", "8"), Map.entry("сен", "9"), Map.entry("окт", "10"),
            Map.entry("ноя", "11"), Map.entry("дек", "12"));

    @Override
    public LocalDateTime parse(String parse) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy, HH:mm");
        String yesterday;
        String today;
        String[] date;
        LocalDateTime ldt = null;
        if (parse.contains("вчера")) {
            date = parse.split(",");
            yesterday = yestDay() + date[1];
            ldt = LocalDateTime.parse(yesterday, formatter);
        } else if (parse.contains("сегодня")) {
            date = parse.split(",");
            today = today() + date[1];
            ldt = LocalDateTime.parse(today, formatter);
        } else {
            ldt = LocalDateTime.parse(parse, formatter);
        }
        return ldt;
    }
}
