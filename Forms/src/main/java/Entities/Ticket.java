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
}
