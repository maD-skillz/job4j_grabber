package ru.job4j.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;

public class ParseToPost {

    public Post parseToPost(String link) throws IOException {
        Document doc = Jsoup.connect(link).get();
        Elements dateParse = doc.select("td[class=msgFooter]");
        String[] date = dateParse.text().split("\\[");
        SqlRuDateTimeParser parser = new SqlRuDateTimeParser();
        return new Post(1, doc.select("td[id=id22132447]").first().text(),
                "https://www.sql.ru/forum/1325330/lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t",
                doc.select("td[class=msgBody]").text(), parser.parse(date[0]));
    }
}

