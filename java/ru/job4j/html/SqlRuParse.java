package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SqlRuParse {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        Elements date = doc.select(".forumTable");
        for (Element i : date) {
            Element ch = i.child(0);
            int size = i.children().size();
            System.out.println(ch.child(0).child(1).text());
            System.out.println(ch.child(0).child(5).text());
        }
    }
}
