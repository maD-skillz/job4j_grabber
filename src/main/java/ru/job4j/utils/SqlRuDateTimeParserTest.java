package ru.job4j.utils;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SqlRuDateTimeParserTest {

    @Test
    public void whenToday() throws Exception {
        SqlRuDateTimeParser par = new SqlRuDateTimeParser();
        String pars = "сегодня, 11:37";
        LocalDateTime time = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth(), 11, 37, 00);
        assertThat(par.parse(pars), is(time));
    }

    @Test
    public void whenYesterday() throws Exception {
        SqlRuDateTimeParser par = new SqlRuDateTimeParser();
        String pars = "вчера, 13:37";
        LocalDateTime time = LocalDateTime.of(LocalDateTime.now().getYear(), LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth() - 1, 13, 37, 00);
        assertThat(par.parse(pars), is(time));
    }

    @Test
    public void whenSomeDate() throws Exception {
        SqlRuDateTimeParser par = new SqlRuDateTimeParser();
        String pars = "11 сен 21, 12:25";
        LocalDateTime time = LocalDateTime.of(2021, Month.SEPTEMBER, 11, 12, 25, 00);
        assertThat(par.parse(pars), is(time));
    }


}