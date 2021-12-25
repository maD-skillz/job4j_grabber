package ru.job4j.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class ParseToPost {

    public Post parseToPost(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        String dateParse = doc.select(".msgFooter").get(0).text();
        String[] date = dateParse.split("\\[");
        SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
        return new Post(0, doc.select(".messageHeader").get(0).ownText(),
                link,
                doc.select(".msgBody").get(1).text(), parser.parse(date[0].trim()));
    }
}

