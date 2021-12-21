package ru.job4j.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.jsoup.nodes.Document;
import java.time.LocalDateTime;
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

    public static List<String> getDate() throws Exception {
        List<String> rsl = new ArrayList<>();
        Document document = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements row = document.select(".postslisttopic");
        for (Element td : row) {
            Element child = td.parent().child(5);
            if (child.text().contains("вчера")) {
                String[] date = child.text().split(",");
                String str = yestDay() + date[1];
                rsl.add(str);
            } else if (child.text().contains("сегодня")) {
                String[] date = child.text().split(",");
                String str = today() + date[1];
                rsl.add(str);
            } else {
                String[] date = child.text().split(" ");
             String[] year = date[2].split(",");
                rsl.add("20" + year[0] + "-" + MONTHS.get(date[1]) + "-" + date[0] + "T" + date[3]);
            }
        }
        return rsl;
    }

    private static final Map<String, String> MONTHS = Map.ofEntries(Map.entry("янв", "1"), Map.entry("фев", "2"),
            Map.entry("мар", "3"), Map.entry("апр", "4"), Map.entry("май", "5"), Map.entry("июн", "6"),
            Map.entry("июл", "7"), Map.entry("авг", "8"), Map.entry("сен", "9"), Map.entry("окт", "10"),
            Map.entry("ноя", "11"), Map.entry("дек", "12"));

    @Override
    public LocalDateTime parse(String parse) throws Exception {
        LocalDateTime ldt = null;
        for (String i : getDate()) {
            ldt = LocalDateTime.parse(i);
        }
        return ldt;
    }
}
