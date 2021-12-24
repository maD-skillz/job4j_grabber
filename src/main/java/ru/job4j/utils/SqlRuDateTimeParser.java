package ru.job4j.utils;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Map<String, Integer> MONTHS = Map.ofEntries(Map.entry("янв", 1), Map.entry("фев", 2),
            Map.entry("мар", 3), Map.entry("апр", 4), Map.entry("май", 5), Map.entry("июн", 6),
            Map.entry("июл", 7), Map.entry("авг", 8), Map.entry("сен", 9), Map.entry("окт", 10),
            Map.entry("ноя", 11), Map.entry("дек", 12));

    @Override
    public LocalDateTime parse(String parse) throws Exception {
        String[] date;
        LocalDateTime ldt = null;
        Month getMonth = LocalDateTime.now().getMonth();
        int getYear = LocalDateTime.now().getYear();
        String[] time;

        if (parse.contains("вчера")) {
            date = parse.split(",");
            time = date[1].split(":");
            int getPrevDay = LocalDateTime.now().getDayOfMonth() - 1;

            ldt = LocalDateTime.of(
                    getYear, getMonth, getPrevDay, Integer.parseInt(time[0].trim()), Integer.parseInt(time[1].trim()));

        } else if (parse.contains("сегодня")) {
            date = parse.split(",");
            time = date[1].split(":");
            int getDay = LocalDateTime.now().getDayOfMonth();

            ldt = LocalDateTime.of(
                    getYear, getMonth, getDay, Integer.parseInt(time[0].trim()), Integer.parseInt(time[1].trim()));

        } else {
            date = parse.split(" ");
            String[] year = date[2].split(",");
            time = date[3].split(":");

            ldt = LocalDateTime.of(2000 + Integer.parseInt(year[0]), MONTHS.get(date[1]),
                        Integer.parseInt(date[0]), Integer.parseInt(time[0]), Integer.parseInt(time[1]));
            }
        return ldt;
    }
}
