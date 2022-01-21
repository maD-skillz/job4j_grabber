package ru.job4j.tdd;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {
    @Ignore
    @Test
    public void whenBuy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test
    public void whenFind() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }
    @Ignore
    @Test
    public void whenGetAccount() {
        Account account = new AccountCinema("Mike", "mike98@mail.ru");
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        Calendar date = Calendar.getInstance();
        date.set(2022, 01, 13, 40, 00);
        Ticket ticket = cinema.buy(account, 4, 5, date);
        assertThat(account, is(new AccountCinema()));
        }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidDate()  {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar expectedDate = Calendar.getInstance();
        expectedDate.set(2022, 13, 21, 14, 00);
        Ticket ticket = cinema.buy(account, 1, 1, expectedDate);
        }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidTicketAndSamePlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        Ticket wrongTicket = cinema.buy(account, 1, 1, date);
    }
    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenInvalidPlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, -1, 99999999, date);
    }
}
