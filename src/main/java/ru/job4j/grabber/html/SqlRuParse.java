package ru.job4j.grabber.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.job4j.grabber.Parse;
import ru.job4j.grabber.Post;
import ru.job4j.grabber.utils.DateTimeParser;

import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {

    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public List<Post> list(String link) throws Exception {
        List<Post> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String sheet = "https://www.sql.ru/forum/job-offers/" + i;
            list.add(detail(sheet));
        }
        return list;
    }

    @Override
    public Post detail(String link) throws Exception {
        Document doc = Jsoup.connect(link).get();
        String dateParse = doc.select(".msgFooter").get(0).text();
        String[] date = dateParse.split("\\[");
        return new Post(doc.select(".messageHeader").get(0).ownText(),
                link,
                doc.select(".msgBody").get(1).text(), dateTimeParser.parse(date[0].trim()));
    }
}
