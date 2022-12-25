package Entities;

import java.sql.Date;
import java.sql.Time;

public class Ticket {
    private int ticket_id;
    private int seat_id;
    private Date date;
    private Time time;
    private int hall_id;
    private boolean is_sold;

    public Ticket(int ticket_id, int seat_id, Date date, Time time, int hall_id, boolean is_sold) {
        this.ticket_id = ticket_id;
        this.seat_id = seat_id;
        this.date = date;
        this.time = time;
        this.hall_id = hall_id;
        this.is_sold = is_sold;
    }

    public int getTicket_id() {
        return ticket_id;
    }

    public void setTicket_id(int ticket_id) {
        this.ticket_id = ticket_id;
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getHall_id() {
        return hall_id;
    }

    public void setHall_id(int hall_id) {
        this.hall_id = hall_id;
    }

    public boolean is_sold() {
        return is_sold;
    }

    public void setIs_sold(boolean is_sold) {
        this.is_sold = is_sold;
    }
}
