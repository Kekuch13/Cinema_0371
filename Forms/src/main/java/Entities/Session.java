package Entities;

import java.sql.Time;
import java.sql.Date;

public class Session {
    private Date date;
    private Time time;
    private int hall_id;
    private int film_id;

    public Session(Date date, Time time, int hall_id, int film_id) {
        this.date = date;
        this.time = time;
        this.hall_id = hall_id;
        this.film_id = film_id;
    }

    @Override
    public String toString() {
        return date + " + " + time + " + " + film_id;
    }
}
