package forms;

import java.sql.Date;
import java.sql.Time;

public class DeleteSessionForm {
    final String form = "DeleteSession";

    public Date date;
    public Time time;
    public int hall_id;

    public DeleteSessionForm() {
    }

    public DeleteSessionForm(Date date, Time time, int hall_id) {
        this.date = date;
        this.time = time;
        this.hall_id = hall_id;
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
}
