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
    public void whenBuy() throws IllegalArgumentException {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test
    public void whenFind() throws IllegalArgumentException {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }
    @Ignore
    @Test
    public void whenGetAccount() throws IllegalArgumentException {
        Account account = new AccountCinema("Mike", "mike98@mail.ru");
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        Calendar date = Calendar.getInstance();
        date.set(2022, 01, 13, 40, 00);
        Ticket ticket = cinema.buy(account, 4, 5, date);
        assertThat(account, is(new AccountCinema()));
        }

    @Ignore
    @Test
    public void whenInvalidDate() throws IllegalArgumentException {
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Calendar expectedDate = Calendar.getInstance();
        expectedDate.set(2022, 01, 21, 14, 00);
        assertNotEquals(date, expectedDate);
        }

    @Ignore
    @Test
    public void whenInvalidTicketAndSamePlace() throws IllegalArgumentException {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        Ticket wrongTicket = cinema.buy(account, 1, 1, date);
        assertFalse(ticket.getRow() == wrongTicket.getRow() && ticket.getColumn() == wrongTicket.getColumn());
    }
    @Ignore
    @Test
    public void whenInvalidPlace() throws IllegalArgumentException {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertFalse(ticket.getRow() > 12);
        assertFalse(ticket.getColumn() > 8);
    }
}
