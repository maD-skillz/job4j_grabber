package ru.job4j.utils;

import java.time.LocalDateTime;
import java.util.*;

public class SqlRuDateTimeParser implements DateTimeParser {

    private static final Map<String, Integer> MONTHS = Map.ofEntries(Map.entry("янв", 1), Map.entry("фев", 2),
            Map.entry("мар", 3), Map.entry("апр", 4), Map.entry("май", 5), Map.entry("июн", 6),
            Map.entry("июл", 7), Map.entry("авг", 8), Map.entry("сен", 9), Map.entry("окт", 10),
            Map.entry("ноя", 11), Map.entry("дек", 12));

    @Override
    public LocalDateTime parse(String parse) {
        LocalDateTime ldtNow = LocalDateTime.now();
        String[] date = parse.split(",");
        String[] time = date[1].split(":");
        LocalDateTime ldt = LocalDateTime.of(
                ldtNow.getYear(), ldtNow.getMonth(),
                ldtNow.getDayOfMonth(), Integer.parseInt(time[0].trim()),
                Integer.parseInt(time[1].trim()));

        if (parse.contains("сегодня")) {
           return ldt;

        } else if (parse.contains("вчера")) {
            return ldt.minusDays(1);

        } else {
            date = parse.split(" ");
            time = date[3].split(":");
            String[] year = date[2].split(",");

            ldt = LocalDateTime.of(2000 + Integer.parseInt(year[0]), MONTHS.get(date[1]),
                        Integer.parseInt(date[0]), Integer.parseInt(time[0]), Integer.parseInt(time[1]));
            }
        return ldt;
    }
}
