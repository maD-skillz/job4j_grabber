package ru.job4j.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class Post {

    private int id;

    private String title;

    private String link;

    private String description;

    private LocalDateTime created;

    public Post(String title, String link, String description, LocalDateTime created) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.created = created;
    }

    public Post(int id, String title, String link, String description, LocalDateTime created) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.created = created;
    }

    private static final Map<String, Integer> MONTHS = Map.ofEntries(Map.entry("янв", 1), Map.entry("фев", 2),
            Map.entry("мар", 3), Map.entry("апр", 4), Map.entry("май", 5), Map.entry("июн", 6),
            Map.entry("июл", 7), Map.entry("авг", 8), Map.entry("сен", 9), Map.entry("окт", 10),
            Map.entry("ноя", 11), Map.entry("дек", 12));

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() throws IOException {
        Document doc = Jsoup.connect(getLink()).get();
        return doc.select("td[id=id22132447]").first().text();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return "https://www.sql.ru/forum/1325330/lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t";
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() throws IOException {
        Document doc = Jsoup.connect(getLink()).get();
        return doc.select("td[class=msgBody]").text();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() throws IOException {
        Document doc = Jsoup.connect(getLink()).get();
        Elements row = doc.select("td[class=msgFooter]");
        String[] date = row.text().split("\\[");
        String[] ldtTime = date[0].split(",");
        String[] hourMinute = ldtTime[1].split(":");
        String[] dayMonthYear = ldtTime[0].split(" ");
        return LocalDateTime.of(2000 + Integer.parseInt(dayMonthYear[2]), MONTHS.get(dayMonthYear[1]),
                Integer.parseInt(dayMonthYear[0]), Integer.parseInt(hourMinute[0].trim()), Integer.parseInt(hourMinute[1].trim()));
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    String gtTitle(Post title) throws Exception {
        setLink("https://www.sql.ru/forum/1325330/lidy-be-fe-senior-cistemnye-analitiki-qa-i-devops-moskva-do-200t");
        Document doc = Jsoup.connect(getLink()).get();
        Elements row = doc.select(".tbody");
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post)) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id && Objects.equals(title, post.title) && Objects.equals(link, post.link) && Objects.equals(created, post.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, link, created);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", title='" + title
                + '\'' + ", link='" + link
                + '\'' + ", description='"
                + description + '\'' + ", created="
                + created + '}';
    }
}
