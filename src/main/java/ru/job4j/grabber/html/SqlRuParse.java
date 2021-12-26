package ru.job4j.grabber.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.Parse;
import ru.job4j.grabber.ParseToPost;
import ru.job4j.grabber.Post;
import ru.job4j.grabber.utils.DateTimeParser;

import java.util.ArrayList;
import java.util.List;

public class SqlRuParse implements Parse {

    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 1; i <= 5; i++) {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers/" + i).get();
            Elements row = doc.select(".postslisttopic");
            for (Element td : row) {
                Element href = td.child(0);
                Element time = td.parent().child(5);
                System.out.println(href.attr("href"));
                System.out.println(href.text());
                System.out.println(time.text());
            }
        }
    }

    @Override
    public List<Post> list(String link) throws Exception {
        List<String> linkList = new ArrayList<>();
        List<Post> postList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            String sheet = "https://www.sql.ru/forum/job-offers/" + i;
            linkList.add(sheet);
        }
        for (String e : linkList) {
            postList.add(detail(e));
        }
        return postList;
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
