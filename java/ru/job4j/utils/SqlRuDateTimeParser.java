package ru.job4j.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Map<String, String> MONTHS = Map.ofEntries(Map.entry("янв", "1"), Map.entry("фев", "2"),
            Map.entry("мар", "3"), Map.entry("апр", "4"), Map.entry("май", "5"), Map.entry("июн", "6"),
            Map.entry("июл", "7"), Map.entry("авг", "8"), Map.entry("сен", "9"), Map.entry("окт", "10"),
            Map.entry("ноя", "11"), Map.entry("дек", "12"));

    @Override
    public LocalDateTime parse(String parse) throws Exception {
        Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT + 3"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
        String[] date;
        LocalDateTime ldt = null;
        if (parse.contains("вчера")) {
            date = parse.split(",");
            cal.add(Calendar.DATE, -1);
            ldt = LocalDateTime.parse(cal.getTime() + date[1], formatter);
        } else if (parse.contains("сегодня")) {
            date = parse.split(",");
            ldt = LocalDateTime.parse(cal.getTime() + date[1], formatter);
        } else {
            date = parse.split(" ");
            String[] year = date[2].split(",");
            if (date[0].length() == 1) {
                ldt = LocalDateTime.parse("20" + year[0] + "-" + MONTHS.get(date[1])
                        + "-" + "0" + date[0] + "T" + date[3], formatter);
            } else {
                ldt = LocalDateTime.parse("20" + year[0] + "-" + MONTHS.get(date[1])
                        + "-" + date[0] + "T" + date[3], formatter);
            }
        }
        return ldt;
    }
}
