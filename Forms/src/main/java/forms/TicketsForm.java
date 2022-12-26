package forms;

import Entities.Ticket;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

public class TicketsForm {
    private final String form = "tickets";
    private Date date;
    private Time time;
    private int hall_id;
    public ArrayList<Ticket> tickets;

    public TicketsForm(Date date, Time time, int hall_id, ArrayList<Ticket> tickets) {
        this.date = date;
        this.time = time;
        this.hall_id = hall_id;
        this.tickets = tickets;
    }

    public String getForm() {
        return form;
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

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }
}
